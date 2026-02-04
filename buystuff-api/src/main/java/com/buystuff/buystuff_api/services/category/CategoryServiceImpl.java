package com.buystuff.buystuff_api.services.category;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.entities.Category;
import com.buystuff.buystuff_api.mappers.category.CategoryMapper;
import com.buystuff.buystuff_api.repositories.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(
		CategoryRepository categoryRepository
	) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Category> addCategories(List<String> categoryCodes) {
		log.info("START: addCategories service");
		
		List<Category> categories =	categoryRepository.findAllByCategoryCodeIn(categoryCodes);
		
		Set<String> existingCodes = 
			categories.stream()
				.map(Category::getCategoryCode)
				.collect(Collectors.toSet());

		List<Category> toInsert = 
			categoryCodes.stream()
				.filter(c -> !existingCodes.contains(c))
				.map(CategoryMapper::toEntity)
				.toList();

		if (!toInsert.isEmpty()) {
			toInsert = categoryRepository.saveAll(toInsert);
			categories.addAll(toInsert);
		}

		log.info("END: addCategories service");
		return categories;
	}
	
}
