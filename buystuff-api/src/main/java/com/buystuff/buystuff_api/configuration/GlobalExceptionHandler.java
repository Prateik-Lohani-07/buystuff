package com.buystuff.buystuff_api.configuration;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.exceptions.BaseHttpException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationErrors(MethodArgumentNotValidException ex) {	
		List<String> errors = ex.getBindingResult().getFieldErrors()
			.stream()
			.map(FieldError::getDefaultMessage)
			.toList();

		String errorMap = String.join(", ", errors);
		HttpStatus status = HttpStatus.BAD_REQUEST;

		return new ResponseEntity<>(
			ApiResponse.error(status.value(), errorMap),
			new HttpHeaders(),
			status
		);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse<Void>> handleUnauthorizedError(AccessDeniedException ex) {
		return new ResponseEntity<>(
			ApiResponse.error(HttpStatus.FORBIDDEN.value(), ex.getMessage() + " baboo"),
			new HttpHeaders(),
			HttpStatus.FORBIDDEN
		);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiResponse<Void>> handleUnauthorizedError(AuthenticationException ex) {
		return new ResponseEntity<>(
			ApiResponse.error(HttpStatus.UNAUTHORIZED.value(), ex.getMessage() + " baboo"),
			new HttpHeaders(),
			HttpStatus.UNAUTHORIZED
		);
	}

    @ExceptionHandler(BaseHttpException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpExceptions(BaseHttpException ex) {
        return new ResponseEntity<>(
			ApiResponse.error(ex.getStatusCode(), ex.getMessage()),
			new HttpHeaders(),
			ex.getStatus()
		);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpExceptions(Exception ex) {
        return new ResponseEntity<>(
			ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()),
			new HttpHeaders(),
			HttpStatus.INTERNAL_SERVER_ERROR
		);
    }
}
