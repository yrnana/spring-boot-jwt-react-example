package com.example.lgcns.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * UserAlreadyExistException
 */
public class UserAlreadyExistException extends AuthenticationException {

	private static final long serialVersionUID = 8118960327050028824L;

	public UserAlreadyExistException(String msg) {
		super(msg);
	}
}