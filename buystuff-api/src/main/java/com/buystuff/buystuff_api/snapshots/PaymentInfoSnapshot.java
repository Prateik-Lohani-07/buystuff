package com.buystuff.buystuff_api.snapshots;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class PaymentInfoSnapshot {
	private UUID paymentInfoId;
	private String accountId;
}
