package com.buystuff.buystuff_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.product.CreateProductDto;
import com.buystuff.buystuff_api.dto.product.ProductDto;
import com.buystuff.buystuff_api.dto.product.ProductFilterDto;
import com.buystuff.buystuff_api.dto.product.UpdateProductDto;
import com.buystuff.buystuff_api.services.product.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public ApiResponse<List<ProductDto>> getAllProducts(
		@RequestParam(defaultValue = "10") int limit,
		@RequestParam(defaultValue = "0") int skip,
		@RequestParam(required = false) List<String> categories,
		@RequestParam(name = "price_start", required = false) Double priceStart,
		@RequestParam(name = "price_end", required = false) Double priceEnd
	) {
		log.info("START: getAllProducts controller");
		
		ProductFilterDto filters = new ProductFilterDto(limit, skip, categories, priceStart, priceEnd);
		List<ProductDto> products = productService.getAllProducts(filters);
		
		log.info("END: getAllProducts controller");
		return ApiResponse.success("Successfully fetched products", products);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{product_id}")
	public ApiResponse<ProductDto> getProductDetails(
		@PathVariable UUID productId
	) {
		log.info("START: getProductDetails controller");
		
		ProductDto product = productService.getProductDetails(productId);

		log.info("END: getProductDetails controller");
		return ApiResponse.success("Successfully fetched product", product);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ApiResponse<ProductDto> addProduct(
		@RequestBody CreateProductDto createProductDto
	) {
		log.info("START: addProduct controller");
		
		ProductDto product = productService.addProduct(createProductDto);

		log.info("END: addProduct controller");
		return ApiResponse.success(HttpStatus.CREATED.value(), "Successfully added product", product);
	}

	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping("/{product_id}")
	public ApiResponse<ProductDto> editProduct(
		@PathVariable(name = "product_id") UUID productId,
		@RequestBody UpdateProductDto updateProductDto
	) {
		log.info("START: editProduct controller");
		
		ProductDto product = productService.editProduct(productId, updateProductDto);

		log.info("END: editProduct controller");
		return ApiResponse.success(
			"Successfully edited product", 
			product
		);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{product_id}")
	public ApiResponse<Void> deleteProduct(
		@PathVariable(name = "product_id") UUID productId
	) {
		log.info("START: deleteProduct controller");
		
		productService.deleteProduct(productId);
		
		log.info("END: deleteProduct controller");
		return ApiResponse.success(
			HttpStatus.NO_CONTENT.value(), 
			"Successfully deleted product", 
			null
		);
	}
}
