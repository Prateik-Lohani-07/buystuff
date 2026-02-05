package com.buystuff.buystuff_api.exceptions;

import org.springframework.http.HttpStatus;

public abstract class BaseHttpException extends RuntimeException {
	private final HttpStatus status;

	protected BaseHttpException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}

	protected BaseHttpException(HttpStatus status, String message, Throwable cause) {
		super(message, cause);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public int getStatusCode() {
		return status.value();
	}
}
