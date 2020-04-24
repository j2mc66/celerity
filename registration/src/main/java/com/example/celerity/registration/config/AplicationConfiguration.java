package com.example.celerity.registration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.celerity.exception.FeignErrorDecoder;

@Configuration
public class AplicationConfiguration {
	
	@Bean
    public FeignErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }
}
