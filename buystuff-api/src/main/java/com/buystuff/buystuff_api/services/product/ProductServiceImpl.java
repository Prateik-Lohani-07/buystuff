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
import com.buystuff.buystuff_api.entities.Category;
import com.buystuff.buystuff_api.entities.Product;
import com.buystuff.buystuff_api.exceptions.NotFoundException;
import com.buystuff.buystuff_api.mappers.product.ProductMapper;
import com.buystuff.buystuff_api.repositories.ProductRepository;
import com.buystuff.buystuff_api.services.category.CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final CategoryService categoryService;

	@Override
	public List<ProductDto> getAllProducts(ProductFilterDto filters) {
		log.info("START: getAllProducts service");
		
		int pageSize = filters.getSkip() / filters.getLimit();
		PageRequest pageRequest = PageRequest.of(pageSize, filters.getLimit());
		int numCategories = filters.getCategories() == null ? 0 : filters.getCategories().size();

		Page<Product> page = productRepository.findAll(
			filters.getCategories(),
			numCategories,
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

	@Override
	public Product getProduct(UUID productId) {
		log.info("START: getProduct service");
		
		Product product = productRepository
		.findById(productId)
		.orElseThrow(() -> new NotFoundException("Product not found: " + productId.toString()));
		
		log.info("START: getProduct service");
		return product;		
	}
}
