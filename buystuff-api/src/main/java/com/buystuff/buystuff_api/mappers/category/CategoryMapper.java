package com.buystuff.buystuff_api.mappers.category;

import com.buystuff.buystuff_api.dto.category.CategoryDto;
import com.buystuff.buystuff_api.entities.Category;

public abstract class CategoryMapper {
	public static Category toEntity(String categoryName) {
		Category entity = new Category();

		entity.setCategoryCode(categoryName.toUpperCase());
		entity.setName(categoryName);

		return entity;
	}

	public static CategoryDto toDto(Category entity) {
		CategoryDto dto = new CategoryDto();

		dto.setCategoryCode(entity.getCategoryCode());
		dto.setName(entity.getName());

		return dto;
	}
}
