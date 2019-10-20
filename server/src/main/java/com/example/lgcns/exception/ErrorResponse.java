package com.example.lgcns.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * ErrorResponse
 */
@Getter
@Setter
public class ErrorResponse {

	private int status;
	private String error;
	private String message;

	public ErrorResponse(HttpStatus status, String message) {
		this.status = status.value();
		this.error = status.getReasonPhrase();
		this.message = message;
	}
}