package com.buystuff.buystuff_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnProcessableException extends BaseHttpException {
	public UnProcessableException(String message) {
		super(HttpStatus.UNPROCESSABLE_ENTITY, message);
	}

	public UnProcessableException(String message, Throwable cause) {
		super(HttpStatus.UNPROCESSABLE_ENTITY, message, cause);
	}
}
