package com.buystuff.buystuff_api.dto.order;

import org.springframework.data.domain.Sort;

import jakarta.validation.constraints.Size;

public record OrderFilterDto(
	Integer size,
	Integer page,
	@Size(min = 1) String sortBy,
	Sort.Direction sortOrder
) { }
