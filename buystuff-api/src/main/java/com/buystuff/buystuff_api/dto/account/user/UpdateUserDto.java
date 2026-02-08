package com.buystuff.buystuff_api.dto.account.user;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserDto {
	@Size(min = 3)
	private String firstName;

	@Size(min = 3)
	private String lastName;
	
	private LocalDate dateOfBirth;
	
	@Size(min = 10)
	private String phone;
	
	@Size(min = 1)
	private String countryCode;
}