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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "cart_id", columnDefinition = "uuid")
	private UUID cartId;

	@OneToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "account_id", nullable = false, unique = true)
	private Account account;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.SET_NULL)
	@JoinColumn(name = "coupon_id")
	private Coupon coupon;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> items = new ArrayList<>();

	public CartItem addToCart(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("Quantity must be positive!");
		}

		CartItem cartItem = items.stream()
			.filter(item -> item.getProduct().equals(product))
			.findFirst()
			.orElse(null);

		if (cartItem != null) {
			cartItem.increaseQty();
			return cartItem;
		}

		cartItem = new CartItem();
		cartItem.setCart(this);
		cartItem.setProduct(product);
		cartItem.setQuantity(1);
		cartItem.setPrice(product.getNetPrice());

		items.add(cartItem);

		return cartItem;
	}

	public void clear() {
		items.clear();
		coupon = null;
	}

	public double calculateTotalCost() {
		double itemsCost = items.stream()
			.mapToDouble(CartItem::getTotalCost)
			.sum();

		double discount = coupon.getDiscountAmount(itemsCost);
		double netTotalCost = Math.max(0, itemsCost - discount);

		return netTotalCost;
	}
}
