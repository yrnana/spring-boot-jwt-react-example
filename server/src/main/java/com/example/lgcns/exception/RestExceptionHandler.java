package com.example.lgcns.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * RestExceptionHandler
 */
@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorResponse handleAuthenticationException(AuthenticationException e) {
		return new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleRuntimeException(RuntimeException e) {
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}
}