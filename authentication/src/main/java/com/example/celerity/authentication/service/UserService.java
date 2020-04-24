package com.example.celerity.authentication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.celerity.authentication.domain.User;
import com.example.celerity.authentication.repository.UserRepository;
import com.example.celerity.exception.ResponseStatusException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	public User create(User user) {
		if(null != user.getId() && userRepository.existsById(user.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return userRepository.save(user);
	}
	
	public User update(User user, Long id) {
		if(userRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
		return userRepository.save(user);
	}
	
	public void delete(Long id) {
		if(!userRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		userRepository.deleteById(id);
	}
}
