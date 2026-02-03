package com.buystuff.buystuff_api.services.product;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.dto.product.ProductFilterDto;
import com.buystuff.buystuff_api.dto.review.CreateReviewDto;
import com.buystuff.buystuff_api.dto.review.UpdateReviewDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Product;
import com.buystuff.buystuff_api.entities.Review;
import com.buystuff.buystuff_api.exceptions.NotFoundException;
import com.buystuff.buystuff_api.mappers.product.review.ReviewMapper;
import com.buystuff.buystuff_api.repositories.ProductRepository;
import com.buystuff.buystuff_api.repositories.ReviewRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ReviewRepository reviewRepository;

	public ProductServiceImpl(
		ProductRepository productRepository,
		ReviewRepository reviewRepository
	) {
		this.productRepository = productRepository;
		this.reviewRepository = reviewRepository;
	}

	@Override
	public List<Product> getAllProducts(ProductFilterDto filters) {
		log.info("START: getAllProducts service");
		
		int pageSize = filters.getSkip() / filters.getLimit();
		PageRequest pageRequest = PageRequest.of(pageSize, filters.getLimit());

		Page<Product> page = productRepository.findAll(
			filters.getCategories(),
			filters.getPriceStart(), 
			filters.getPriceEnd(),
			pageRequest
		);
			
		log.info("END: getAllProducts service");
		return page.getContent();
	}

	@Override
	public Product getProductDetails(UUID productId) {
		log.info("START: getProductDetails service");
		
		Product product = getProduct(productId);

		log.info("END: getProductDetails service");
		return product;
	}

	@Override
	public Review addReview(UUID productId, Account account, CreateReviewDto createReviewDto) {
		log.info("START: addReview service");
		
		Product product = getProduct(productId);		
		Review review = ReviewMapper.toEntity(createReviewDto, account, product);
		reviewRepository.save(review);
		
		log.info("END: addReview service");
		return review;
	}

	@Override
	public void editReview(UUID productId, Account account, UUID reviewId, UpdateReviewDto updateReviewDto) {
		log.info("START: editReview service");
		
		// check for product existence
		getProduct(productId);
		
		Review review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> new NotFoundException("Review not found."));
		
		ReviewMapper.updateEntity(updateReviewDto, review);
		reviewRepository.save(review);

		log.info("END: editReview service");
	}

	@Override
	public void deleteReview(UUID productId, Account account, UUID reviewId) {
		log.info("START: deleteReview service");
		
		// check for product existence
		getProduct(productId);

		Review review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> new NotFoundException("Review not found."));
			
		reviewRepository.delete(review);
		
		log.info("END: deleteReview service");
	}

	private Product getProduct(UUID productId) {
		Product product = productRepository
			.findById(productId)
			.orElseThrow(() -> new NotFoundException("Product not found: " + productId.toString()));

		return product;		
	}
}
