package com.example.securitytest.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private long validityInMs; // 1 hour

    // Secret key to sign the JWT
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Generate JWT token
    @SuppressWarnings("deprecation")
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey()) // Automatically uses HS256 since we're using the SecretKey
                .compact();
    }

    // Get username from token
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser() // Changed parser() as per 0.12.6 API
                .verifyWith(getSigningKey()) // Verifies the signature with the signing key
                .build()
                .parseSignedClaims(token) // Use parseSignedClaims instead of parseClaims
                .getPayload(); // Extract the Claims from the token

        return claims.getSubject();
    }

    // Validate JWT token
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean validateToken(String token) {
        try {
            Jwts.parser() // Create a parser for token
                    .verifyWith(getSigningKey()) // Verifies the signature
                    .build()
                    .parseSignedClaims(token); // Parse and validate the token
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Log token validation errors
            e.printStackTrace();
        }
        return false;
    }
}
