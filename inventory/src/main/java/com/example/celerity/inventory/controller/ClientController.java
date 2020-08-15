package com.example.celerity.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.celerity.dto.CustomPage;
import com.example.celerity.dto.UserDto;
import com.example.celerity.inventory.integration.UserClient;

@RestController
@RequestMapping("/invoice")
public class ClientController {
	
	@Autowired
	private UserClient userClient;
	
	@GetMapping
	public CustomPage<UserDto> register() {
		
		for(int i=0;i<100;i++) {
			userClient.get();
		}
		
		return userClient.get();
		
	}
}
