package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
