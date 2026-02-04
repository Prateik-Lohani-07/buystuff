package com.buystuff.buystuff_api.dto.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * The filters that the user puts for products.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductFilterDto {
	private int limit;
	private int skip;

	/*
	 * The categories for which the user filters. It's only raw strings and doesn't map to the actual categories
	 * stored in the database. Separate checks to be kept for that.
	 */
	private List<String> categories;
	private double priceStart;
	private double priceEnd;
}
