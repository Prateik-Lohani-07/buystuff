package com.buystuff.buystuff_api.dto.account;

import java.util.UUID;

import com.buystuff.buystuff_api.abstract_classes.BaseResponseDto;
import com.buystuff.buystuff_api.dto.account.user.UserDto;
import com.buystuff.buystuff_api.enums.Role;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccountDto extends BaseResponseDto {
	@NotNull
	private UUID accountId;

	@Email
	private String email;

	@NotBlank
	private Role role;

	@Valid
	@NotNull
	private UserDto user;
}
