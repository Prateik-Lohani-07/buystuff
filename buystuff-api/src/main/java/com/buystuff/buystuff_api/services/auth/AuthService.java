package com.buystuff.buystuff_api.services.auth;

import java.util.UUID;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.authentication.LoginDto;
import com.buystuff.buystuff_api.dto.authentication.SignupDto;

public interface AuthService {
    ApiResponse<String> authenticate(LoginDto loginDto);
	void signup(SignupDto signupDto);
	void changePassword(UUID accountId, String oldPassword, String newPassword);
}
