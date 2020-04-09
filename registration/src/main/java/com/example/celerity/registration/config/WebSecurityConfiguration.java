package com.example.celerity.registration.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/actuator/**")
			.antMatchers("/v2/api-docs")
			.antMatchers("/configuration/**")
			.antMatchers("/swagger-resources/**")		
			.antMatchers("/swagger-ui.html")
			.antMatchers("/webjars/**")
			.antMatchers("/api-docs/**");
	}
}
