package com.example.celerity.authentication.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.celerity.domain.User;
import com.example.celerity.dto.UserDto;
import com.example.celerity.authentication.service.UserService;
import com.querydsl.core.types.Predicate;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/me")
    public UserDto get(final Principal principal) {
		User user = userService.findByUsername(principal.getName());
		return UserDto.convertToDto(user);
    }

	@GetMapping("/user")
	public Page<UserDto> findAll(@QuerydslPredicate(root=User.class) Predicate predicate, Pageable pageable) {
		return userService.findAll(predicate, pageable).map(UserDto::convertToDto);
	}
	
	@GetMapping("/user/{id}")
	public UserDto findById(@PathVariable Long id) {
		User user = userService.findById(id);
		return UserDto.convertToDto(user);
	}

	@PostMapping("/user")
	public UserDto create(@RequestBody @Valid User user) {
		User userCreate =  userService.create(user);
		return UserDto.convertToDto(userCreate);
	}

	@PutMapping("/user/{id}")
	public UserDto update(@RequestBody @Valid User user, @PathVariable Long id) {
		User userUpdate = userService.update(user, id);
		return UserDto.convertToDto(userUpdate);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.delete(id);
	}
	
}
