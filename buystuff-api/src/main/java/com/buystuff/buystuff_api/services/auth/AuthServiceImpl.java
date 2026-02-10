package com.buystuff.buystuff_api.services.auth;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.dto.authentication.LoginDto;
import com.buystuff.buystuff_api.dto.authentication.SignupDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.UserPrincipal;
import com.buystuff.buystuff_api.exceptions.BadRequestException;
import com.buystuff.buystuff_api.mappers.account.AccountMapper;
import com.buystuff.buystuff_api.services.account.AccountService;
import com.buystuff.buystuff_api.services.jwt.JwtTokenService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of the <code>AuthService</code> interface.
 * The <code>AuthServiceImpl</code> class provides 3 core functionalities for users:
 * - login
 * - registration
 * - password change
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
	private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;

	@Override
	public String authenticate(LoginDto loginDto) {
        var unAuthenticatedObj = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(unAuthenticatedObj);
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
        String jwtToken = jwtTokenService.generateToken(userPrincipal);

        return jwtToken;
	}

    @Override
    public String registerUser(SignupDto signupDto) {
        log.info("START: registerUser service");

        String email = signupDto.getEmail(), rawPassword = signupDto.getPassword();
        String passwordHash = passwordEncoder.encode(rawPassword);

		Account account = AccountMapper.toEntity(signupDto, passwordHash);

        accountService.saveAccount(account);

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(email);
        loginDto.setPassword(rawPassword);

        String jwtToken = authenticate(loginDto);

        log.info("END: registerUser service");
        return jwtToken;
    }

	@Override
    @Transactional
	public void changePassword(UUID accountId, String newPassword, String oldPassword) throws Exception {
        Account account = accountService.getAccount(accountId);
        String passwordHash = account.getPasswordHash();

        // don't allow if incorrect existing password entered
        if (!comparePasswords(oldPassword, passwordHash)) {
            throw new BadCredentialsException("Incorrect password");
        }

        // don't allow if new password and existing password are the same -> must be a different password
        if (comparePasswords(newPassword, passwordHash)) {
            throw new BadRequestException("New password must be different from the old password");
        }

        String newPasswordHash = passwordEncoder.encode(newPassword);
        account.setPasswordHash(newPasswordHash);
	}

	private boolean comparePasswords(String input, String actual) {
		return passwordEncoder.matches(input, actual);
	}
}
