package com.buystuff.buystuff_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseHttpException {
	public NotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(HttpStatus.NOT_FOUND, message, cause);
	}
}