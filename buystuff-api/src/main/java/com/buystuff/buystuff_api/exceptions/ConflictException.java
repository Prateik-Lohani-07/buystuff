package com.buystuff.buystuff_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends BaseHttpException {
	public ConflictException(String message) {
		super(HttpStatus.CONFLICT, message);
	}

	public ConflictException(String message, Throwable cause) {
		super(HttpStatus.CONFLICT, message, cause);
	}
}
