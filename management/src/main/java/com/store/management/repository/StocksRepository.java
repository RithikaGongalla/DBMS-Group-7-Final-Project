package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.model.Stocks;

public interface StocksRepository extends JpaRepository<Stocks, Long> {
	
	Stocks findByProductId(long prodId);

}
