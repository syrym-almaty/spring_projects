package com.example.securitytest.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    // Store roles as a comma-separated string (e.g., "ROLE_USER,ROLE_ADMIN")
    private String roles;

    // Convert roles string to GrantedAuthority list
    public Collection<? extends GrantedAuthority> getRoles() {
        if (roles == null || roles.isEmpty()) {
            return Collections.emptyList();
        }
        String[] roleArray = roles.split(",");
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : roleArray) {
            authorities.add(new SimpleGrantedAuthority(role.trim()));
        }
        return authorities;
    }
}
