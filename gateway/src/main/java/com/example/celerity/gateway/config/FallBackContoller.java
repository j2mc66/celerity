package com.example.celerity.gateway.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallBackContoller {

	@GetMapping()
	public Mono<String> fallback() {
	  return Mono.just("fallback");
	}
}
