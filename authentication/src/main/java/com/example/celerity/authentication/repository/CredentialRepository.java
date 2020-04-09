package com.example.celerity.authentication.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.celerity.authentication.domain.Credential;

public interface CredentialRepository extends JpaRepository<Credential,Long> {
	
	Credential findByName(String name);
}
