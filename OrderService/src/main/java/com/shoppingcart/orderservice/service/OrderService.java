package com.shoppingcart.orderservice.service;

import java.util.List;

import com.shoppingcart.orderservice.dto.OrderRequestDto;
import com.shoppingcart.orderservice.dto.PaymentQueueDto;
import com.shoppingcart.orderservice.entity.Order;

public interface OrderService {

	public Order saveOrder(OrderRequestDto orderRequestDto);

	public Order getOrderById(Integer orderId);
	
	public List<Order> findByCustomerId(Integer customerId);

	public void updateOrderStatus(PaymentQueueDto paymentQueue);
}
