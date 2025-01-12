package com.shoppingcart.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shoppingcart.orderservice.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	// Fin Detail by orderId
	@Query("SELECT u FROM OrderDetail u WHERE u.order.orderId = ?1")
	List<OrderDetail> findByOrderId(Integer orderId);

}
