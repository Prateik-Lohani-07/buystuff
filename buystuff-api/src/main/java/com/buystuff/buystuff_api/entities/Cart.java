package com.buystuff.buystuff_api.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.buystuff.buystuff_api.abstract_classes.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "carts")
public class Cart extends BaseEntity {
	@Id
	@Column(name = "account_id")
	private UUID accountId;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "account_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Account account;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.SET_NULL)
	@JoinColumn(name = "coupon_id")
	private Coupon coupon;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> items = new ArrayList<>();

	public double calculateTotalCost() {
		double itemsCost = items.stream()
			.mapToDouble(CartItem::getTotalCost)
			.sum();

		double discount = coupon.getDiscountAmount(itemsCost);
		double netTotalCost = Math.max(0, itemsCost - discount);

		return netTotalCost;
	}
}
