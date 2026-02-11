package com.buystuff.buystuff_api.exceptions;

import org.springframework.http.HttpStatus;

public class UnProcessableException extends BaseHttpException {
	public UnProcessableException(String message) {
		super(HttpStatus.UNPROCESSABLE_ENTITY, message);
	}

	public UnProcessableException(String message, Throwable cause) {
		super(HttpStatus.UNPROCESSABLE_ENTITY, message, cause);
	}
}
