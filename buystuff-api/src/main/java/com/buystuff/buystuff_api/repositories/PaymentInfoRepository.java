package com.buystuff.buystuff_api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buystuff.buystuff_api.entities.PaymentInfo;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, UUID> {
	
}
