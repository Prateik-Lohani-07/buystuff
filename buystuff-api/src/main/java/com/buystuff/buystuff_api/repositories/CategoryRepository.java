package com.buystuff.buystuff_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buystuff.buystuff_api.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
	List<Category> findAllByCategoryCodeIn(List<String> codes);

}
