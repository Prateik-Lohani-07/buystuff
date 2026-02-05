package com.buystuff.buystuff_api.services.product;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.dto.product.CreateProductDto;
import com.buystuff.buystuff_api.dto.product.ProductDto;
import com.buystuff.buystuff_api.dto.product.ProductFilterDto;
import com.buystuff.buystuff_api.dto.product.UpdateProductDto;
import com.buystuff.buystuff_api.dto.review.CreateReviewDto;
import com.buystuff.buystuff_api.dto.review.ReviewDto;
import com.buystuff.buystuff_api.dto.review.UpdateReviewDto;
import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.Category;
import com.buystuff.buystuff_api.entities.Product;
import com.buystuff.buystuff_api.entities.Review;
import com.buystuff.buystuff_api.exceptions.NotFoundException;
import com.buystuff.buystuff_api.mappers.product.ProductMapper;
import com.buystuff.buystuff_api.mappers.product.review.ReviewMapper;
import com.buystuff.buystuff_api.repositories.ProductRepository;
import com.buystuff.buystuff_api.repositories.ReviewRepository;
import com.buystuff.buystuff_api.services.category.CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ReviewRepository reviewRepository;
	private final CategoryService categoryService;

	@Override
	public List<ProductDto> getAllProducts(ProductFilterDto filters) {
		log.info("START: getAllProducts service");
		
		int pageSize = filters.getSkip() / filters.getLimit();
		PageRequest pageRequest = PageRequest.of(pageSize, filters.getLimit());

		Page<Product> page = productRepository.findAll(
			filters.getCategories(),
			filters.getPriceStart(), 
			filters.getPriceEnd(),
			pageRequest
		);
			
		List<Product> products = page.getContent();
		List<ProductDto> productDto = 
			products
				.stream()
				.map(ProductMapper::toDto)
				.toList();

		log.info("END: getAllProducts service");
		return productDto;
	}

	@Override
	public ProductDto getProductDetails(UUID productId) {
		log.info("START: getProductDetails service");
		
		Product product = getProduct(productId);
		ProductDto productDto = ProductMapper.toDto(product);

		log.info("END: getProductDetails service");
		return productDto;
	}

	@Override
	public ReviewDto addReview(UUID productId, Account account, CreateReviewDto createReviewDto) {
		log.info("START: addReview service");
		
		Product product = getProduct(productId);		
		Review review = ReviewMapper.toEntity(createReviewDto, account, product);
		reviewRepository.save(review);
		
		ReviewDto response = ReviewMapper.toDto(review);
		
		log.info("END: addReview service");
		return response;
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

	@Override
	@Transactional
	public ProductDto addProduct(CreateProductDto createProductDto) {
		log.info("START: addProduct service");
		
		List<String> categoryCodes = createProductDto.getCategories();
		List<Category> categories = categoryService.addCategories(categoryCodes);

		Product product = ProductMapper.toEntity(createProductDto, categories);
		product = productRepository.save(product);

		ProductDto productDto = ProductMapper.toDto(product);
		
		log.info("END: addProduct service");
		return productDto;
	}

	@Override
	@Transactional
	public ProductDto editProduct(UUID productId, UpdateProductDto updateProductDto) {
		log.info("START: editProduct service");
		
		List<Category> categories = null;
		List<String> categoryCodes = updateProductDto.getCategories();

		if (categoryCodes != null) {
			categories = categoryService.addCategories(updateProductDto.getCategories());
		}

		Product product = getProduct(productId);
		ProductMapper.updateEntity(updateProductDto, product, categories);
		product = productRepository.save(product);

		ProductDto productDto = ProductMapper.toDto(product);

		log.info("END: editProduct service");
		return productDto;
	}

	@Override
	public void deleteProduct(UUID productId) {
		log.info("START: deleteProduct service");
		log.debug(productId.toString());

		productRepository.deleteById(productId);

		log.info("END: deleteProduct service");
	}

	private Product getProduct(UUID productId) {
		Product product = productRepository
			.findById(productId)
			.orElseThrow(() -> new NotFoundException("Product not found: " + productId.toString()));

		return product;		
	}
}
