package com.buystuff.buystuff_api.services.product;

import java.util.List;
import java.util.UUID;

import com.buystuff.buystuff_api.dto.product.CreateProductDto;
import com.buystuff.buystuff_api.dto.product.ProductDto;
import com.buystuff.buystuff_api.dto.product.ProductFilterDto;
import com.buystuff.buystuff_api.dto.product.UpdateProductDto;
import com.buystuff.buystuff_api.dto.review.CreateReviewDto;
import com.buystuff.buystuff_api.dto.review.ReviewDto;
import com.buystuff.buystuff_api.dto.review.UpdateReviewDto;
import com.buystuff.buystuff_api.entities.Account;

public interface ProductService {
	List<ProductDto> getAllProducts(ProductFilterDto filters);
	ProductDto getProductDetails(UUID productId);
	ProductDto addProduct(CreateProductDto createProductDto);
	ProductDto editProduct(UUID productId, UpdateProductDto updateProductDto);
	void deleteProduct(UUID productId);
	ReviewDto addReview(UUID productId, Account account, CreateReviewDto createReviewDto);
	void editReview(UUID productId, Account account, UUID reviewId, UpdateReviewDto updateReviewDto);
	void deleteReview(UUID productId, Account account, UUID reviewId);
}
