package com.example.celerity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStatusException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus status;

	@Nullable
	private final String message;
	
	@Nullable
	private String description;
	
	/**
	 * Constructor with a response status.
	 * @param status the HTTP status (required)
	 */
	public ResponseStatusException(HttpStatus status) {
		this(status, null, null, null);
	}

	/**
	 * Constructor with a response status and a reason to add to the exception
	 * message as explanation.
	 * @param status the HTTP status (required)
	 * @param message the associated message (optional)
	 */
	public ResponseStatusException(HttpStatus status, @Nullable String message) {
		this(status, message, null, null);
	}

	/**
	 * Constructor with a response status and a reason to add to the exception
	 * message as explanation, as well as a nested exception.
	 * @param status the HTTP status (required)
	 * @param message the associated message (optional)
	 * @param description the associated description (optional)
	 */
	public ResponseStatusException(HttpStatus status, @Nullable String message, @Nullable String description) {
		this(status, message, description, null);
	}
	
	/**
	 * Constructor with a response status and a reason to add to the exception
	 * message as explanation, as well as a nested exception.
	 * @param status the HTTP status (required)
	 * @param message the associated message (optional)
	 * @param description the associated description (optional)
	 * @param cause a nested exception (optional)
	 */
	public ResponseStatusException(HttpStatus status, @Nullable String message, @Nullable String description, @Nullable Throwable cause) {
		super(null, cause);
		Assert.notNull(status, "HttpStatus is required");
		this.status = status;
		this.message = message;
		this.description = description;
	}

}
