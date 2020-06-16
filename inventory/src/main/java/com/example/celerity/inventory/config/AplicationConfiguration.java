package com.example.celerity.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

import com.example.celerity.exception.FeignErrorDecoder;

@Configuration
public class AplicationConfiguration {
	
	@Bean
    public FeignErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }
	
	@Bean
	public OAuth2FeignRequestInterceptor oAuth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext) {
		return new OAuth2FeignRequestInterceptor(oauth2ClientContext);
	}
}
