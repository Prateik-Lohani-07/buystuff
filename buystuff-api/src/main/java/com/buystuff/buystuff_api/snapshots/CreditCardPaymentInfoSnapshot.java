package com.buystuff.buystuff_api.snapshots;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardPaymentInfoSnapshot extends PaymentInfoSnapshot {
	private String cardNumberLast4Digits;
	private LocalDateTime expiryDate;
	private String cardHolder;
}
