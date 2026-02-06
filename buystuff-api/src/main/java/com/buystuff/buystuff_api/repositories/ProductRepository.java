package com.buystuff.buystuff_api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.buystuff.buystuff_api.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
	/*
	 * Using a join here along with the IN keyword is fine here under the 
	 * assumption that this app will have at-max (worst case) 100-200 categories.
	 * For most normal use-cases, 8-10 categories should suffice- in which case the
	 * performance overhead would be negligible.
	 */
	@Query(value = """
		SELECT DISTINCT p 
		FROM Product p
		JOIN p.categories c
		WHERE p.isActive = true
			AND (:categories IS NULL OR c.categoryCode IN :categories)
			AND (:price_start IS NULL OR (p.price - p.discount) >= :price_start)
			AND (:price_end IS NULL OR (p.price - p.discount) <= :price_end)
		GROUP BY p
		HAVING (:categories IS NULL OR COUNT(DISTINCT c.categoryCode) = :category_count)
	""")
	Page<Product> findAll(
		@Param("categories") List<String> categories,
		@Param("category_count") Integer categoryCount,
		@Param("price_start") Double priceStart,
		@Param("price_end") Double priceEnd,
		Pageable pageable
	);
}
