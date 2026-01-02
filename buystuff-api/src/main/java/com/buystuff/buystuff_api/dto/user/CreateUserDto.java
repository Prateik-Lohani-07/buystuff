package com.buystuff.buystuff_api.dto.user;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserDto {
	@NotBlank
	private String name;
	
	@NotNull
	private LocalDate dob;
	
	@NotBlank
	private String phone;
	
	@NotBlank
	private String countryCode;
}
