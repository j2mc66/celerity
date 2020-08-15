package com.example.celerity.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String to;
	
	private String from;

	private String subject;
	
	private String content;
	
	private LocalDateTime date;
	
	private Map<String, Object> model;
	
	public Message() {
		
	}
	
	public Message(String content) {
		this.content = content;
	}
}
