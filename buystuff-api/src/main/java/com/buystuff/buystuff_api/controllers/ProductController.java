package com.buystuff.buystuff_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buystuff.buystuff_api.dto.ApiResponse;
import com.buystuff.buystuff_api.dto.product.CreateProductDto;
import com.buystuff.buystuff_api.dto.product.ProductDto;
import com.buystuff.buystuff_api.dto.product.ProductFilterDto;
import com.buystuff.buystuff_api.dto.product.UpdateProductDto;
import com.buystuff.buystuff_api.dto.review.CreateReviewDto;
import com.buystuff.buystuff_api.dto.review.ReviewDto;
import com.buystuff.buystuff_api.dto.review.UpdateReviewDto;
import com.buystuff.buystuff_api.entities.UserPrincipal;
import com.buystuff.buystuff_api.services.product.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/")
	public ApiResponse<List<ProductDto>> getAllProducts(
		@RequestParam(defaultValue = "10") int limit,
		@RequestParam(defaultValue = "0") int skip,
		@RequestParam(required = false) List<String> categories,
		@RequestParam(name = "price_start", required = false) Double priceStart,
		@RequestParam(name = "price_end", required = false) Double priceEnd
	) {
		ProductFilterDto filters = new ProductFilterDto(limit, skip, categories, priceStart, priceEnd);
		List<ProductDto> products = productService.getAllProducts(filters);

		return ApiResponse.success("Successfully fetched products", products);
	}

	@GetMapping("/{product_id}")
	public ApiResponse<ProductDto> getProduct(
		@PathVariable UUID productId
	) {
		ProductDto product = productService.getProductDetails(productId);
		return ApiResponse.success("Successfully fetched product", product);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ApiResponse<ProductDto> addProduct(
		@RequestBody CreateProductDto createProductDto
	) {
		ProductDto product = productService.addProduct(createProductDto);
		return ApiResponse.success("Successfully added product", product);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping("/{product_id}")
	public ApiResponse<ProductDto> editProduct(
		@PathVariable(name = "product_id") UUID productId,
		@RequestBody UpdateProductDto updateProductDto
	) {
		ProductDto product = productService.editProduct(productId, updateProductDto);
		return ApiResponse.success("Successfully edited product", product);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{product_id}")
	public ApiResponse<Void> deleteProduct(
		@PathVariable(name = "product_id") UUID productId
	) {
		productService.deleteProduct(productId);
		return ApiResponse.success("Successfully deleted product", null);
	}
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/{product_id}/reviews")
	public ApiResponse<ReviewDto> addReview(
		@PathVariable(name = "product_id") UUID productId,
		@RequestBody CreateReviewDto createReviewDto,
		@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		ReviewDto review = productService.addReview(productId, userPrincipal.getAccount(), createReviewDto);
		return ApiResponse.success("Successfully added review", review);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@PatchMapping("/{product_id}/reviews/{review_id}")
	public ApiResponse<Void> editReview(
		@PathVariable(name = "product_id") UUID productId,
		@PathVariable(name = "review_id") UUID reviewId,
		@RequestBody UpdateReviewDto updateReviewDto,
		@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		productService.editReview(productId, userPrincipal.getAccount(), reviewId, updateReviewDto);
		return ApiResponse.success("Successfully edited review", null);
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@DeleteMapping("/{product_id}/reviews/{review_id}")
	public ApiResponse<Void> deleteReview(
		@PathVariable(name = "product_id") UUID productId,
		@PathVariable(name = "review_id") UUID reviewId,
		@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		productService.deleteReview(productId, userPrincipal.getAccount(), reviewId);
		return ApiResponse.success("Successfully deleted review", null);
	}
}
