package com.buystuff.buystuff_api.snapshots;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressSnapshot {
	private String flatOrBlock;
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String country;
	private String pincode;
}
