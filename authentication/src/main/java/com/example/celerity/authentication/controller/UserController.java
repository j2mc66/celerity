package com.example.celerity.authentication.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.celerity.authentication.domain.User;
import com.example.celerity.authentication.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/me")
    public User get(final Principal principal) {
		return userService.findByUsername(principal.getName());
    }

	@GetMapping("/user")
	public List<User> findAll() {
		return userService.findAll();
	}
	
	@GetMapping("/user/{id}")
	public User findById(@PathVariable Long id) {
		return userService.findById(id);		
	}

	@PostMapping("/user")
	public User create(@RequestBody User user) {
		return userService.create(user);
	}

	@PutMapping("/user/{id}")
	public User update(@RequestBody User user, @PathVariable Long id) {
		return userService.update(user, id);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.delete(id);
	}
	
}
