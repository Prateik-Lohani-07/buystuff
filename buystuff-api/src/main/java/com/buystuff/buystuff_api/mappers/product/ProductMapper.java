package com.buystuff.buystuff_api.mappers.product;

import java.util.List;

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
		dto.setAvgRating(entity.getAvgRating());
		dto.setCategories(
			entity.getCategories().stream()
				.map(c -> c.getCategoryCode())
				.toList()
		);

		dto.setNumberOfReviews(entity.getReviews().size());

		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());

		return dto;
	}

	public static void updateEntity(UpdateProductDto dto, Product entity, List<Category> categories) {
		if (dto.getName() != null) {
			entity.setName(dto.getName());
		}

		if (dto.getPrice() != null) {
			entity.setPrice(dto.getPrice());
		}
		
		if (dto.getDiscount() != null) {
			entity.setDiscount(dto.getDiscount());
		}

		if (dto.getStock() != null) {
			entity.setStock(dto.getStock());
		}

		if (dto.getDescription() != null) {
			entity.setDescription(dto.getDescription());
		}

		if (dto.getIsActive() != null) {
			entity.setIsActive(dto.getIsActive());
		}

		if (categories != null) {
			entity.setCategories(categories);
		}

	}

	public static Product toEntity(CreateProductDto dto, List<Category> categories) {
		Product entity = new Product();
		
		entity.setName(dto.getName());
		entity.setProductCode(dto.getProductCode());
		entity.setPrice(dto.getPrice());
		entity.setDiscount(dto.getDiscount());
		entity.setStock(dto.getStock());
		entity.setDescription(dto.getDescription());
		entity.setAvgRating(0.0);
		entity.setIsActive(true);
		entity.setCategories(categories);

		return entity;
	}

}
