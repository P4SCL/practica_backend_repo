package com.api.backend.models.excepciones;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ApiException {
	
	private final String mensaje;
	private final HttpStatus httpstatus;
	private final Date date;

	public ApiException(String mensaje, HttpStatus httpstatus,Date date){
		this.mensaje = mensaje;
		this.httpstatus = httpstatus;
		this.date = date;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public HttpStatus getHttpstatus() {
		return httpstatus;
	}
	
	public Date getDate() {
		return date;
	}
	
}
