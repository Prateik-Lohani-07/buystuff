package com.buystuff.buystuff_api.services.auth;

import java.util.UUID;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.authentication.LoginDto;
import com.buystuff.buystuff_api.dto.authentication.SignupDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.services.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

	@Override
	public ApiResponse<String> authenticate(LoginDto loginDto) {
        var token = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        String jwtToken = jwtTokenService.generateToken(authentication);

        return ApiResponse.success("Login successful", jwtToken);
	}

	@Override
	public void signup(SignupDto signupDto) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'signup'");
	}

	@Override
	public void changePassword(UUID accountId, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
	}

	private boolean comparePasswords(String actual, String input) {
		return false;
	}
}
