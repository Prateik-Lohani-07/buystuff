package com.buystuff.buystuff_api.dto.account.address;


import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAddressDto {
	@NotBlank
	private String flatOrBlock;
	
	@NotBlank
	private String line1;
	
	private String line2;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String state;
	
	@NotBlank
	private String country;
	
	@NotBlank
	private String pincode;
}
