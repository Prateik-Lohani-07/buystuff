package com.buystuff.buystuff_api.services.auth;

import java.util.UUID;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.authentication.LoginDto;
import com.buystuff.buystuff_api.dto.authentication.SignupDto;
import com.buystuff.buystuff_api.dto.user.CreateUserDto;

public interface AuthService {
    ApiResponse<String> authenticate(LoginDto loginDto);
	void changePassword(UUID accountId, String oldPassword, String newPassword) throws Exception;
    ApiResponse<String> registerUser(SignupDto signupDto);
}
