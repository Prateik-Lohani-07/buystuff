package com.buystuff.buystuff_api.dto.account.payment_info.upi;

import com.buystuff.buystuff_api.dto.account.payment_info.PaymentInfoDto;
import com.buystuff.buystuff_api.enums.PaymentType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UPIDto extends PaymentInfoDto {
	private String upiId;

	public UPIDto() {
		super(PaymentType.UPI);
	}
}
