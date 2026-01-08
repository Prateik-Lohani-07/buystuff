package com.buystuff.buystuff_api.dto.user;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserDto {
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String phone;
	private String countryCode;
}