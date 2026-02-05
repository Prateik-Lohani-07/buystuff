package com.buystuff.buystuff_api.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends BaseHttpException {
	public ConflictException(String message) {
		super(HttpStatus.CONFLICT, message);
	}

	public ConflictException(String message, Throwable cause) {
		super(HttpStatus.CONFLICT, message, cause);
	}
}
