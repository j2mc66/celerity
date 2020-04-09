package com.example.celerity.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {

	private String timestamp;
	private int status;
	private String error;
	private String message;
	private String description;
	private String path;
	
}
