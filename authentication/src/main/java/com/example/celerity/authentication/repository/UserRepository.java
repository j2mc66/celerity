package com.example.celerity.authentication.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.celerity.authentication.domain.User;

public interface UserRepository extends JpaRepository<User,Long>, QuerydslPredicateExecutor<User> {
	
	Optional<User> findByUsername(String name);
}
