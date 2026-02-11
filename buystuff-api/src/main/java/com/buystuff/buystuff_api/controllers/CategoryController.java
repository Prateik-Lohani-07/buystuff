package com.buystuff.buystuff_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.category.CategoryDto;
import com.buystuff.buystuff_api.services.category.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public ApiResponse<List<CategoryDto>> getCategories() {
		log.info("START: getCategories controller");

		List<CategoryDto> categoryDtos = categoryService.getCategories();

		log.info("END: getCategories controller");
		return ApiResponse.success("Successfully fetched categories", categoryDtos);
	}
}
