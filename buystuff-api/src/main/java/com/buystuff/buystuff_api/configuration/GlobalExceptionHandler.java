package com.buystuff.buystuff_api.configuration;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.buystuff.buystuff_api.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {	
		List<String> errors = ex.getBindingResult().getFieldErrors()
			.stream()
			.map(FieldError::getDefaultMessage)
			.toList();

		String errorMap = joinErrorMessages(errors);
		HttpStatus status = HttpStatus.BAD_REQUEST;

		return new ResponseEntity<>(
			ApiResponse.error(status.value(), errorMap),
			new HttpHeaders(),
			status
		);
	}

	private String joinErrorMessages(List<String> errors) {
		return String.join(", ", errors);
	}

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<ApiResponse<Object>> handleAllExceptions(Exception ex) {
    //     ApiResponse<Object> resp = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());

    //     return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    // }
}
