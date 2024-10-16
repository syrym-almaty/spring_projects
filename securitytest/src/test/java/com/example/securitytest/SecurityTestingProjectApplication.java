// src/main/java/com/example/securitytest/SecurityTestingProjectApplication.java

package com.example.securitytest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.securitytest.entity.UserEntity;
import com.example.securitytest.repository.UserRepository;

@SpringBootApplication
public class SecurityTestingProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityTestingProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.findByUsername("admin") == null) {
				UserEntity admin = new UserEntity();
				admin.setUsername("admin");
				admin.setPassword(passwordEncoder.encode("password"));
				admin.setRoles("ROLE_USER,ROLE_ADMIN");
				userRepository.save(admin);
				System.out.println("Admin user created with username 'admin' and password 'password'");
			}
		};
	}
}
