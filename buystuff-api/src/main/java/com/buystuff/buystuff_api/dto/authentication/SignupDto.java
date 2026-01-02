package com.buystuff.buystuff_api.dto.authentication;

import com.buystuff.buystuff_api.dto.user.CreateUserDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupDto {
	@Email
	private String email;

	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$",
		message = "Password must be at least 8 characters and include uppercase, lowercase, digit, and special character"
	)
	@NotBlank
	private String password;

	@Valid
	@NotNull
	private CreateUserDto userInfo;
}
