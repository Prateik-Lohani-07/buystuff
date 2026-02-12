package com.buystuff.buystuff_api.dto.product;

import java.util.List;

import com.buystuff.buystuff_api.enums.SortOrder;

import jakarta.validation.constraints.Size;

/*
 * The filters that the user puts for products.
 */
public record ProductFilterDto (
	Integer size,
	Integer page,
	@Size(min = 1) String sortBy,
	SortOrder sortOrder,
	List<String> categories,
	Double priceStart,
	Double priceEnd
) { }
