package com.buystuff.buystuff_api.dto.account.address;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateAddressDto {
	private String flatOrBlock;
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String country;
	private String pincode;
}
