package com.buystuff.buystuff_api.snapshots;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the state of the product at the time the order was placed
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductSnapshot {
	private String productCode;
	private Double price;
	private Double discount;
}
