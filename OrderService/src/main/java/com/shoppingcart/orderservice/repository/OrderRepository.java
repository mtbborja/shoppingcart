package com.shoppingcart.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoppingcart.orderservice.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	// Find Orders by customerId
	@Query("SELECT u FROM Order u WHERE u.customerId = ?1")
	public List<Order> findByCustomerId(Integer customerId);
}
