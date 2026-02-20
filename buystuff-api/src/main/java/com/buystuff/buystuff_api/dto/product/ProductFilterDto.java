package com.buystuff.buystuff_api.dto.product;

import java.util.List;

import org.springframework.data.domain.Sort;

import jakarta.validation.constraints.Size;

/*
 * The filters that the user puts for products.
 * TODO: revert back to class 
 */
public record ProductFilterDto (
	Integer size,
	Integer page,
	@Size(min = 1) String sortBy,
	Sort.Direction sortOrder,
	List<String> categories,
	Double priceStart,
	Double priceEnd
) { }
