package com.example.celerity.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.celerity.authentication.repository.UserRepository;
import com.example.celerity.domain.User;
import com.example.celerity.exception.ResponseStatusException;
import com.querydsl.core.types.Predicate;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User findByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado", "Prueba descripcion"));
	}
	
	public Page<User> findAll(Predicate predicate, Pageable pageable) {
		return userRepository.findAll(predicate, pageable);
	}
	
	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
	}
	
	public User create(User user) {
		if(null != user.getId() && userRepository.existsById(user.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
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
