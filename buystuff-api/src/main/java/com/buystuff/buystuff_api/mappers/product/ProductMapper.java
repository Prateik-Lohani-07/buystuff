package com.buystuff.buystuff_api.mappers.product;

import com.buystuff.buystuff_api.dto.category.CategoryDto;
import com.buystuff.buystuff_api.dto.product.ProductDto;
import com.buystuff.buystuff_api.entities.Product;

public abstract class ProductMapper {
	public static ProductDto toDto(Product entity) {
		ProductDto dto = new ProductDto();

		dto.setProductId(entity.getProductId());
		dto.setName(entity.getName());
		dto.setProductCode(entity.getProductCode());
		dto.setPrice(entity.getPrice());
		dto.setDiscount(entity.getDiscount());
		dto.setStock(entity.getStock());
		dto.setDescription(entity.getDescription());
		dto.setIsActive(entity.getIsActive());
		dto.setCategories(
			entity.getCategories().stream()
				.map(c -> 
					new CategoryDto(c.getCategoryCode(), c.getName())
				)
				.toList()
		);

		return dto;
	}
}
