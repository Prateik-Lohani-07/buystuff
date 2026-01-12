package com.buystuff.buystuff_api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.authentication.LoginDto;
import com.buystuff.buystuff_api.dto.authentication.SignupDto;
import com.buystuff.buystuff_api.services.auth.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginDto loginDto) {
        try {
            return authService.authenticate(loginDto);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/signup")
    public ApiResponse<String> signup(@RequestBody SignupDto signupDto) {
        try {
            return authService.registerUser(signupDto);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
