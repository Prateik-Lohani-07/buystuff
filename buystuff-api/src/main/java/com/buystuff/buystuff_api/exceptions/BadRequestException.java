package com.buystuff.buystuff_api.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseHttpException {
	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}

	public BadRequestException(String message, Throwable cause) {
		super(HttpStatus.BAD_REQUEST, message, cause);
	}
}

