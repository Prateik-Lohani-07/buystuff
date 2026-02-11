package com.buystuff.buystuff_api.entities;

import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "cart_items")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "item_id", columnDefinition = "uuid")
	private UUID itemId;

	@Column(name = "price", columnDefinition = "numeric")
	private double price;

	@Column(name = "quantity", columnDefinition = "integer")
	private Integer quantity;

	@ManyToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public void increaseQty() {
		quantity = Math.min(quantity + 1, product.getStock());
	}
	
	public void decreaseQty() {
		quantity = Math.max(quantity - 1, 0);
	}

	public double getTotalCost() {
		return price * quantity;
	}
}
