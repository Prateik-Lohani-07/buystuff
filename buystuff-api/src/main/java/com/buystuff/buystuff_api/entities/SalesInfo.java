package com.buystuff.buystuff_api.entities;

import com.buystuff.buystuff_api.abstract_classes.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	@Column(name = "product_code", columnDefinition = "uuid")
	private String productCode;

	@OneToOne
	@JoinColumn(
		name = "product_code", 
		referencedColumnName = "product_code",
		insertable = false,
		updatable = false
	)
	private Product product;
	
	@Column(name = "quantity_sold", columnDefinition = "integer")
	private Integer quantitySold;
	
	@Column(name = "revenue", columnDefinition = "numeric")
	private Integer revenue;
}
