package com.buystuff.buystuff_api.services.auth;

import java.util.UUID;

import com.buystuff.buystuff_api.dto.authentication.LoginDto;
import com.buystuff.buystuff_api.dto.authentication.SignupDto;

public interface AuthService {
    String authenticate(LoginDto loginDto);
	void changePassword(UUID accountId, String oldPassword, String newPassword) throws Exception;
    String registerUser(SignupDto signupDto);
}
