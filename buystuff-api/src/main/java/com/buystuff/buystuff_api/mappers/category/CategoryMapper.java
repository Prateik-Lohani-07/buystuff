package com.buystuff.buystuff_api.mappers.category;

import com.buystuff.buystuff_api.entities.Category;

public abstract class CategoryMapper {
	public static Category toEntity(String categoryCode) {
		Category entity = new Category();

		entity.setCategoryCode(categoryCode);
		entity.setName(categoryCode.toLowerCase());

		return entity;
	}
}
