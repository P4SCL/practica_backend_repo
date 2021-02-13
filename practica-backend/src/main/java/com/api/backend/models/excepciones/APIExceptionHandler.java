package com.api.backend.models.excepciones;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
@ControllerAdvice
public class APIExceptionHandler {
	
	private final HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR; //500
	private final HttpStatus badRequest = HttpStatus.BAD_REQUEST;//400
	private final HttpStatus metodoNoSoportado = HttpStatus.METHOD_NOT_ALLOWED;//405
	

	
	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
		//1.-Crear paypload que contendrá los detalles de la excepción
		ApiException exception = new ApiException(e.getMessage(), internalServerError,internalServerError.value(), new Date());
		//2.-Return response entity
		return new ResponseEntity<>(exception,internalServerError);
	}
	
	@ExceptionHandler(value = {MethodArgumentTypeMismatchException.class,NumberFormatException.class})
	public ResponseEntity<Object> restringirCadenas(MethodArgumentTypeMismatchException e){
		//1.-Crear paypload que contendrá los detalles de la excepción
		ApiException exception = new ApiException("No se puede convertir la cadena a número ", badRequest,badRequest.value(), new Date());
		//2.-Return response entity
		return new ResponseEntity<>(exception,badRequest);
	}
	
	@ExceptionHandler(value = {HttpMessageNotReadableException.class})
	public ResponseEntity<Object> ausenciaCuerpo(HttpMessageNotReadableException e){
		//1.-Crear paypload que contendrá los detalles de la excepción
		ApiException exception = new ApiException("No se envió el cuerpo de la petición ", badRequest,badRequest.value(), new Date());
		//2.-Return response entity
		return new ResponseEntity<>(exception,badRequest);
	}
	
	@ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<Object> metodoNoSoportado(HttpRequestMethodNotSupportedException e){
		//1.-Crear paypload que contendrá los detalles de la excepción
		ApiException exception = new ApiException("Método ".concat(e.getMethod()).concat(" no está soportado para este ENDPOINT") , metodoNoSoportado,metodoNoSoportado.value(), new Date());
		//2.-Return response entity
		return new ResponseEntity<>(exception,metodoNoSoportado);
	}
	
//	@ExceptionHandler({DataIntegrityViolationException.class})
//	public ResponseEntity<Object> campoUnico(DataIntegrityViolationException e){
//		//1.-Crear paypload que contendrá los detalles de la excepción
//		ApiException exception = new ApiException("El email ya existe en la BD",internalServerError,internalServerError.value(), new Date());
//		//2.-Return response entity
//		return new ResponseEntity<>(exception,internalServerError);
//	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<Object> argumentosNoValidos(MethodArgumentNotValidException e){
		//1.-Crear paypload que contendrá los detalles de la excepción
		ApiException exception = new ApiException(null,internalServerError,internalServerError.value(), new Date());
		//2.-Return response entity
		return new ResponseEntity<>(exception,internalServerError);
	}
	
}
