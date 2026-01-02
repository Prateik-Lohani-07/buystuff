package com.buystuff.buystuff_api.dto.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginDto {
	@Email
	private String email;

	@NotBlank
	private String password;
}
