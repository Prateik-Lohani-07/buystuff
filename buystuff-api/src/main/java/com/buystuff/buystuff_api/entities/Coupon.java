package com.buystuff.buystuff_api.entities;

import java.util.UUID;

import org.hibernate.annotations.NaturalId;

import com.buystuff.buystuff_api.abstract_classes.BaseEntity;
import com.buystuff.buystuff_api.enums.DiscountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "coupons")
public class Coupon extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "coupon_id", columnDefinition = "uuid")
	private UUID couponId;

	@NaturalId
	@Column(name = "coupon_code", columnDefinition = "varchar")
	private String couponCode;

	@Column(name = "name", columnDefinition = "varchar")
	private String name;

	@Column(name = "discount_value", columnDefinition = "numeric")
	private Double discountValue;

	@Enumerated(EnumType.STRING)
	@Column(name = "discount_type", length=20, nullable=false)	
	private DiscountType discountType;

	public Double getDiscountAmount(Double price) {
		Double amount;
		
		if (discountType == DiscountType.FLAT) 
			amount = discountValue;
		else 
			amount = price * discountValue;

		return amount;
	}
}
