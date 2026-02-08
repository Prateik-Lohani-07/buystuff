package com.buystuff.buystuff_api.dto.account;

import com.buystuff.buystuff_api.dto.account.user.UpdateUserDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UpdateAccountDtos {
	@NotBlank
	private String email;

	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$",
		message = "Password must be at least 8 characters and include uppercase, lowercase, digit, and special character"
	)
	@NotBlank
	private String password;

	@Valid
	private UpdateUserDto updateUserDto;
}
