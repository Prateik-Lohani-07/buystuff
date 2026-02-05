package com.buystuff.buystuff_api.configuration;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(
		HttpServletRequest request,
		HttpServletResponse response,
		AccessDeniedException accessDeniedException
	) throws IOException {
		log.error("Forbidden error: {}", accessDeniedException.getMessage());

		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType("application/json");

		response.getWriter().write(
			new ObjectMapper().writeValueAsString(
				ApiResponse.error(
					HttpStatus.FORBIDDEN.value(), 
					accessDeniedException.getMessage() + " ahoy"
				)
			)
		);
	}
}
