package com.example.securitytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.securitytest.entity.UserEntity;
import com.example.securitytest.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired  // Constructor Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    // Additional business logic methods can be added here
}
