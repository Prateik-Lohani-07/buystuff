package com.buystuff.buystuff_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.account.AccountDto;
import com.buystuff.buystuff_api.dto.authentication.LoginDto;
import com.buystuff.buystuff_api.dto.authentication.SignupDto;
import com.buystuff.buystuff_api.entities.UserPrincipal;
import com.buystuff.buystuff_api.services.account.AccountService;
import com.buystuff.buystuff_api.services.auth.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
	private final AccountService accountService;

	@ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public ApiResponse<String> login(
		@Valid @RequestBody 
		LoginDto loginDto
	) {
		log.info("START: login controller");
		
		String jwtToken = authService.authenticate(loginDto);
		
		log.info("END: login controller");
		return ApiResponse.success("Login successful", jwtToken);
    }

	@ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ApiResponse<String> signup(
		@Valid @RequestBody 
		SignupDto signupDto
	) {
		log.info("START: signup controller");
		
		String jwtToken = authService.registerUser(signupDto);

		log.info("END: signup controller");
		return ApiResponse.success("Signup successful", jwtToken);
    }

	@ResponseStatus(HttpStatus.OK)
    @GetMapping("/me")
    public ApiResponse<AccountDto> getCurrentUser(
		@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		log.info("START: getCurrentUser controller");
		
		AccountDto accountDto = accountService.getAccountDetails(userPrincipal.getId());
		
		log.info("END: getCurrentUser controller");
		return ApiResponse.success("Successfully fetched account details", accountDto);
    }
}
