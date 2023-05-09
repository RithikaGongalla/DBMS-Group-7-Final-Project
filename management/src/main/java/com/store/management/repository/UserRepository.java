package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.model.Users;


public interface UserRepository extends JpaRepository<Users, Long> {

	Users findFirstByUserName(String userName);

	Users findFirstByUserId(long userId);
	
}
