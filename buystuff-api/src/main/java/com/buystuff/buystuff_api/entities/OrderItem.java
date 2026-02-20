package com.buystuff.buystuff_api.entities;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import com.buystuff.buystuff_api.snapshots.ProductSnapshot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(
	name="order_items",
	uniqueConstraints = { @UniqueConstraint(columnNames = { "order_id", "product_id" }) }
)
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "item_id", columnDefinition = "uuid")
	private UUID itemId;

	@ManyToOne()
	@JoinColumn(name = "product_id")
	private Product product;
	
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "product_snapshot", columnDefinition = "jsonb")
	private ProductSnapshot productSnapshot;
	
	@Column(name = "quantity", columnDefinition = "integer")
	private Integer quantity;

	@ManyToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	public Double getTotalCost() {
		Double netPrice = productSnapshot.getPrice() - productSnapshot.getDiscount();
		return netPrice * quantity;
	}
}
