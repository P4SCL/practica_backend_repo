package com.api.backend.models.excepciones;

import org.springframework.dao.DataAccessException;

@SuppressWarnings("serial")
public class ApiRequestException extends DataAccessException{
	
	public ApiRequestException(String message) {
		super(message);
	}
}
