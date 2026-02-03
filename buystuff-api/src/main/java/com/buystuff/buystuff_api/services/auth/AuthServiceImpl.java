package com.buystuff.buystuff_api.services.auth;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.authentication.LoginDto;
import com.buystuff.buystuff_api.dto.authentication.SignupDto;
import com.buystuff.buystuff_api.dto.user.CreateUserDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.User;
import com.buystuff.buystuff_api.entities.UserPrincipal;
import com.buystuff.buystuff_api.enums.Role;
import com.buystuff.buystuff_api.mappers.user.UserMapper;
import com.buystuff.buystuff_api.services.account.AccountService;

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
	public ApiResponse<String> authenticate(LoginDto loginDto) {
        var unAuthenticatedObj = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(unAuthenticatedObj);
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
        String jwtToken = jwtTokenService.generateToken(userPrincipal.getAccount());

        return ApiResponse.success("Login successful", jwtToken);
	}

    @Override
    @Transactional
    public ApiResponse<String> registerUser(SignupDto signupDto) {
        log.info("START: registerUser service");

        Account account = new Account();
        String email = signupDto.getEmail(), rawPassword = signupDto.getPassword();
        CreateUserDto createUserDto = signupDto.getUserInfo();
        User user = UserMapper.toEntity(createUserDto, account);

        String passwordHash = passwordEncoder.encode(rawPassword);

        account.setEmail(email);
        account.setPasswordHash(passwordHash);
        account.setUser(user);
        account.setRole(Role.CUSTOMER);

        account = accountService.saveAccount(account);
        user.setAccountId(account.getAccountId());
        user.setAccount(account);

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(email);
        loginDto.setPassword(rawPassword);

        ApiResponse<String> response = authenticate(loginDto);

        log.info("END: registerUser service");
        return response;
    }

	@Override
    @Transactional
	public void changePassword(UUID accountId, String newPassword, String oldPassword) throws Exception {
        Account account = accountService.getAccount(accountId);
        String passwordHash = account.getPasswordHash();

        // don't allow if incorrect existing password entered
        if (!comparePasswords(oldPassword, passwordHash)) {
            throw new Exception("Incorrect password");
        }

        // don't allow if new password and existing password are the same -> must be a different password
        if (comparePasswords(newPassword, passwordHash)) {
            throw new Exception("New password must be different from the old password");
        }

        String newPasswordHash = passwordEncoder.encode(newPassword);
        account.setPasswordHash(newPasswordHash);
	}

	private boolean comparePasswords(String input, String actual) {
		return passwordEncoder.matches(input, actual);
	}
}
