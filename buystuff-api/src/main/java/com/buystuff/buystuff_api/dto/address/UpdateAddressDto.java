package com.buystuff.buystuff_api.dto.address;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateAddressDto {
	@NotNull
	private UUID addressId;

	private String flatOrBlock;
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String country;
	private String pincode;
	
	@NotNull
	private UUID accountId;
}
