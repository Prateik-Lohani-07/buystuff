package com.buystuff.buystuff_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buystuff.buystuff_api.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
	List<Category> findAllByCategoryCodeIn(List<String> codes);

}
