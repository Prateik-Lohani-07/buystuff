package com.buystuff.buystuff_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends BaseHttpException {
	public ForbiddenException(String message) {
		super(HttpStatus.FORBIDDEN, message);
	}

	public ForbiddenException(String message, Throwable cause) {
		super(HttpStatus.FORBIDDEN, message, cause);
	}
}
