package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
	
	Seller findById(long id);

	Seller findBySellerName(String id);

}
