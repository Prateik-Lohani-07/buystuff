package com.buystuff.buystuff_api.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

@DiscriminatorValue("UPI")

@Table(name = "upi_payment_info")
public class UPIPaymentInfo extends PaymentInfo {
	private static final String REGEX = "^[a-zA-Z0-9.-]{2,256}@[a-zA-Z][a-zA-Z]{2,64}$";
	
	private static final Pattern PATTERN = Pattern.compile(REGEX);

	@Column(name = "upi_id", columnDefinition = "varchar", nullable = false)
	private String upiId;

    @Override
    protected Boolean isValid() {
		Matcher matcher = PATTERN.matcher(this.upiId);
		return matcher.matches();
    }
}
