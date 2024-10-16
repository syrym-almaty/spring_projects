// src/main/java/com/example/securitytest/util/KeyGenerator.java

package com.example.securitytest.util;

import java.security.SecureRandom;

import io.jsonwebtoken.io.Encoders;

public class KeyGenerator {
    public static void main(String[] args) {
        byte[] keyBytes = new byte[32]; // 256 bits for HS256
        SecureRandom random = new SecureRandom();
        random.nextBytes(keyBytes);
        String base64Key = Encoders.BASE64.encode(keyBytes);
        System.out.println("Base64-encoded key: " + base64Key);
    }
}
