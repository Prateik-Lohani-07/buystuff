package com.buystuff.buystuff_api.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor

@DiscriminatorValue("CREDIT_CARD")

@Table(name = "credit_card_payment_info")
public class CreditCardPaymentInfo extends PaymentInfo {
	@Column(name = "card_number_last_4_digits", length = 4, columnDefinition = "varchar", nullable = false)
	private String cardNumberLast4Digits;

	@Column(name = "expiry_date", columnDefinition = "timestamp", nullable = false)
	private LocalDateTime expiryDate;
	
	@Column(name = "card_holder", columnDefinition = "varchar", nullable = false)
	private String cardHolder;

	@Override
    protected Boolean isValid() {
        return LocalDateTime.now().isBefore(this.expiryDate);
    }

}
