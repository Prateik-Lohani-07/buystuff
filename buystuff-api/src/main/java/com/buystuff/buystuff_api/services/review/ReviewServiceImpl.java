package com.buystuff.buystuff_api.services.review;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.dto.product.review.CreateReviewDto;
import com.buystuff.buystuff_api.dto.product.review.ReviewDto;
import com.buystuff.buystuff_api.dto.product.review.UpdateReviewDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Product;
import com.buystuff.buystuff_api.entities.Review;
import com.buystuff.buystuff_api.exceptions.NotFoundException;
import com.buystuff.buystuff_api.mappers.product.review.ReviewMapper;
import com.buystuff.buystuff_api.repositories.ReviewRepository;
import com.buystuff.buystuff_api.services.account.AccountService;
import com.buystuff.buystuff_api.services.product.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	private final ReviewRepository reviewRepository;
	private final ProductService productService;
	private final AccountService accountService;

	@Override
	public ReviewDto addReview(UUID productId, UUID accountId, CreateReviewDto createReviewDto) {
		log.info("START: addReview service");
		
		Product product = productService.getProduct(productId);
		Account account = accountService.getAccount(accountId);
		Review review = ReviewMapper.toEntity(createReviewDto, account, product);
		reviewRepository.save(review);
		
		ReviewDto response = ReviewMapper.toDto(review, accountId);
		
		log.info("END: addReview service");
		return response;
	}

	@Override
	public ReviewDto editReview(UUID productId, UUID accountId, UUID reviewId, UpdateReviewDto updateReviewDto) {
		log.info("START: editReview service");
		
		// check for product existence
		productService.getProduct(productId);
		
		Review review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> new NotFoundException("Review not found."));
		
		ReviewMapper.updateEntity(updateReviewDto, review);
		review = reviewRepository.save(review);
		ReviewDto reviewDto = ReviewMapper.toDto(review, accountId);

		log.info("END: editReview service");
		return reviewDto;
	}

	@Override
	public void deleteReview(UUID productId, UUID accountId, UUID reviewId) {
		log.info("START: deleteReview service");
		
		// check for product existence
		productService.getProduct(productId);

		Review review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> new NotFoundException("Review not found."));
			
		reviewRepository.delete(review);
		
		log.info("END: deleteReview service");
	}

	
}
