package com.buystuff.buystuff_api.services.product;

import java.util.List;
import java.util.UUID;

import com.buystuff.buystuff_api.dto.product.CreateProductDto;
import com.buystuff.buystuff_api.dto.product.ProductFilterDto;
import com.buystuff.buystuff_api.dto.product.UpdateProductDto;
import com.buystuff.buystuff_api.dto.review.CreateReviewDto;
import com.buystuff.buystuff_api.dto.review.UpdateReviewDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Product;
import com.buystuff.buystuff_api.entities.Review;

public interface ProductService {
	List<Product> getAllProducts(ProductFilterDto filters);
	Product getProductDetails(UUID productId);
	Product addProduct(CreateProductDto createProductDto) throws Exception;
	void editProduct(UUID productId, UpdateProductDto updateProductDto);
	void deleteProduct(UUID productId);
	Review addReview(UUID productId, Account account, CreateReviewDto createReviewDto);
	void editReview(UUID productId, Account account, UUID reviewId, UpdateReviewDto updateReviewDto);
	void deleteReview(UUID productId, Account account, UUID reviewId);
}
