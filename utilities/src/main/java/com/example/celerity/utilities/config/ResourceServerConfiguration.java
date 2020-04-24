package com.example.celerity.utilities.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfFilter;

import com.example.celerity.exception.ExceptionHandlerOauthFilter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Value("${security.oauth2.resource.id}")
	private String resourceId;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors()
		.and()
			.csrf().disable()
			.addFilterAfter(new ExceptionHandlerOauthFilter(), CsrfFilter.class)
			.authorizeRequests()
				.antMatchers("/actuator/**").permitAll()
				.antMatchers("/v2/api-docs").permitAll()
				.antMatchers("/configuration/**").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/api-docs/**").permitAll()
			.anyRequest().authenticated()			
		.and()
			.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
		.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				
	}
	
	@Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
         resources.resourceId(resourceId);
    }
}
