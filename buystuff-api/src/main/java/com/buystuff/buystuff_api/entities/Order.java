package com.buystuff.buystuff_api.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.buystuff.buystuff_api.abstract_classes.BaseEntity;
import com.buystuff.buystuff_api.enums.OrderStatus;
import com.buystuff.buystuff_api.snapshots.AddressSnapshot;
import com.buystuff.buystuff_api.snapshots.PaymentInfoSnapshot;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "order_id", columnDefinition = "uuid")
	private UUID orderId;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> items = new ArrayList<>();

	@Column(name = "discount", columnDefinition = "numeric")
	private Double discount;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "shipping_address_snapshot", columnDefinition = "jsonb")
	private AddressSnapshot shippingAddressSnapshot;
	
	@Column(name = "delivered_at", columnDefinition = "timestamp without time zone")
	private LocalDateTime deliveredAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 20, nullable = false)
	private OrderStatus status;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "payment_info_snapshot", columnDefinition = "jsonb")
	private PaymentInfoSnapshot paymentInfoSnapshot;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	public Double calculateTotalCost() {
		Double itemsCost = items.stream()
			.mapToDouble(OrderItem::getTotalCost)
			.sum();

		Double netTotalCost = itemsCost - discount;
		return netTotalCost;
	}
}
