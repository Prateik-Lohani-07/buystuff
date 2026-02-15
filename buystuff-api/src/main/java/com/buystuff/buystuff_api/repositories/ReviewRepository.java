package com.buystuff.buystuff_api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buystuff.buystuff_api.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
	
}
