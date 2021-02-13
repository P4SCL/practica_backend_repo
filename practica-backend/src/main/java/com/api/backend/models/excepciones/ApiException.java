package com.api.backend.models.excepciones;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ApiException {
	
	private final String mensaje;
	private final HttpStatus httpstatus;
	private final Date date;
	private final Integer code;

	public ApiException(String mensaje, HttpStatus httpstatus,Integer code, Date date){
		this.mensaje = mensaje;
		this.httpstatus = httpstatus;
		this.date = date;
		this.code = code;
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
	public Integer getCode() {
		return code;
	}
}
