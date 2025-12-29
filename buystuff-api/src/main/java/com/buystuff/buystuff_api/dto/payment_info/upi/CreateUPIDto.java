package com.buystuff.buystuff_api.dto.payment_info.upi;

import com.buystuff.buystuff_api.dto.payment_info.CreatePaymentInfoDto;
import com.buystuff.buystuff_api.enums.PaymentType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUPIDto extends CreatePaymentInfoDto {
	private String upiId;

	public CreateUPIDto() {
		super(PaymentType.UPI);
	}
}
