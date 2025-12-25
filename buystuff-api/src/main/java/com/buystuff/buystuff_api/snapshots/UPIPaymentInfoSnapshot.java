package com.buystuff.buystuff_api.snapshots;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UPIPaymentInfoSnapshot extends PaymentInfoSnapshot {
	private String upiId;
}
