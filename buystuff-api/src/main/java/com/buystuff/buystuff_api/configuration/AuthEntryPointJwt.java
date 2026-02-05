package com.buystuff.buystuff_api.configuration;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.buystuff.buystuff_api.dto.ApiResponse;

@Component
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	@Override
	public void commence(
		HttpServletRequest request,
		HttpServletResponse response,
		AuthenticationException authException
	) throws IOException, ServletException {
		log.error("Unauthorized error: {}", authException.getMessage());

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");

		response.getWriter().write(
			new ObjectMapper().writeValueAsString(
				ApiResponse.error(
					HttpStatus.UNAUTHORIZED.value(), 
					"Invalid credentials ahoy"
				)
			)
		);
	}
}
