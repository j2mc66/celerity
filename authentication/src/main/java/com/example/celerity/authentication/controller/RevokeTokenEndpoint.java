package com.example.celerity.authentication.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FrameworkEndpoint
public class RevokeTokenEndpoint {

	@Autowired
	ConsumerTokenServices tokenServices;

	@RequestMapping(method = RequestMethod.DELETE, value = "/oauth/token")
	@ResponseBody
	public void revokeToken(HttpServletRequest request, @RequestParam String token) {
		if (token != null && !token.isEmpty()) {
			tokenServices.revokeToken(token);
		}
	}
}
