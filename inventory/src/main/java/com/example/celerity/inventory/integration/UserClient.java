package com.example.celerity.inventory.integration;

import java.time.LocalDateTime;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value = "registration")
@RequestMapping("/user")
public interface UserClient {
	
	@GetMapping("/")
	LocalDateTime register();

}
