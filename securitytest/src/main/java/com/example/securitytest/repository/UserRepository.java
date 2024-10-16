package com.example.securitytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.securitytest.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
