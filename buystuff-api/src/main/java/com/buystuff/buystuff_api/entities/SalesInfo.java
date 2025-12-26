package com.buystuff.buystuff_api.entities;

import java.time.Instant;
import java.util.UUID;

import com.buystuff.buystuff_api.abstract_classes.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "sales_info")
public class SalesInfo extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "info_id", columnDefinition = "uuid")
	private UUID infoId;
	
	@Column(name = "product_code", columnDefinition = "varchar", unique = true)
	private String productCode;
	
	@Column(name = "product_name", columnDefinition = "varchar")
	private String productName;

	@Column(name = "quantity", columnDefinition = "integer")
	private Integer quantity;
	
	@Column(name = "price", columnDefinition = "numeric")
	private Integer price;

	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
