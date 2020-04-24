package com.example.celerity.inventory.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.celerity.inventory.integration.UserClient;

@RestController
@RequestMapping("/invoice")
public class ClientController {
	
	@Autowired
	private UserClient userClient;
	
	@GetMapping
	public LocalDateTime register() {
		
		LocalDateTime apiResponse = userClient.register();
		
		return apiResponse;
	}
}
