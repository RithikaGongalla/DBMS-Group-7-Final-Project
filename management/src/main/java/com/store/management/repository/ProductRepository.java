package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.model.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {

	Products findByProductNameAndSellerId(String productName, long sellerId);

}
