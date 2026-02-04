package com.buystuff.buystuff_api.mappers.product;

import java.util.List;

import com.buystuff.buystuff_api.dto.category.CategoryDto;
import com.buystuff.buystuff_api.dto.product.CreateProductDto;
import com.buystuff.buystuff_api.dto.product.ProductDto;
import com.buystuff.buystuff_api.dto.product.UpdateProductDto;
import com.buystuff.buystuff_api.entities.Category;
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

	public static void updateEntity(UpdateProductDto dto, Product entity, List<Category> categories) {
		
	}

	public static Product toEntity(CreateProductDto dto, List<Category> categories) {
		Product entity = new Product();
		
		entity.setName(dto.getName());
		entity.setProductCode(dto.getProductCode());
		entity.setPrice(dto.getPrice());
		entity.setDiscount(dto.getDiscount());
		entity.setStock(dto.getStock());
		entity.setDescription(dto.getDescription());
		entity.setIsActive(entity.getIsActive());
		entity.setCategories(categories);

		return entity;
	}

}
