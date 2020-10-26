package com.github.gmsdeveloper04.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -2806411215129594157L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
