package com.shoppingcart.paymentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shoppingcart.paymentservice.entity.PaymentOrder;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Integer> {

	@Query("SELECT u FROM PaymentOrder u WHERE u.status = ?1")
	List<PaymentOrder> findByStatus(String status);

}
