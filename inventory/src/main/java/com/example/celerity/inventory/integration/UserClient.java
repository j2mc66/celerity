package com.example.celerity.inventory.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.celerity.dto.CustomPage;
import com.example.celerity.dto.UserDto;


@FeignClient(value = "authentication")
@RequestMapping("/user")
public interface UserClient {
	
	@GetMapping("/")
	CustomPage<UserDto> get();

}
