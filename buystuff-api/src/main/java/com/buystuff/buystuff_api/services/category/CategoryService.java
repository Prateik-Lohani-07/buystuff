package com.buystuff.buystuff_api.services.category;

import java.util.List;

import com.buystuff.buystuff_api.dto.category.CategoryDto;
import com.buystuff.buystuff_api.entities.Category;

public interface CategoryService {
	public List<Category> addCategories(List<String> categoryCodes);
	public List<CategoryDto> getCategories();
}
