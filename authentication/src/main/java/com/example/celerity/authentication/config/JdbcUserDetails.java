package com.example.celerity.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.celerity.domain.User;
import com.example.celerity.authentication.repository.UserRepository;

public class JdbcUserDetails implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = userRepository.findByUsername(username)
    			.orElseThrow(() -> new UsernameNotFoundException("User "+username+" can not be found"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(),true,true,true, user.getAuthorities());
    }
}
