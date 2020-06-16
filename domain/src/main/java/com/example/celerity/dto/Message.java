package com.example.celerity.dto;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String to;
	
	private String from;

	private String subject;
	
	private String content;
	
	private Map<String, Object> model;
	
	public Message() {
		
	}
	
	public Message(String content) {
		this.content = content;
	}
}
