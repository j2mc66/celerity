package com.example.celerity.authentication.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.celerity.authentication.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	Optional<User> findByUsername(String name);
}
