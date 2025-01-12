package com.shoppingcart.orderservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shoppingcart.orderservice.dto.OrderDetailDto;
import com.shoppingcart.orderservice.dto.OrderRequestDto;
import com.shoppingcart.orderservice.dto.PaymentQueueDto;
import com.shoppingcart.orderservice.entity.Order;
import com.shoppingcart.orderservice.entity.OrderDetail;
import com.shoppingcart.orderservice.exception.ApiException;
import com.shoppingcart.orderservice.repository.OrderDetailRepository;
import com.shoppingcart.orderservice.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepository;

	public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
		this.orderRepository = orderRepository;
		this.orderDetailRepository = orderDetailRepository;
	}

	@Override
	@Transactional
	public Order saveOrder(OrderRequestDto orderRequestDto) {

		// Save the Order entity
		Order order = new Order();
		order.setCustomerId(orderRequestDto.getCustomerId());
		order = orderRepository.save(order);

		// Save the OrderDetails entities
		List<OrderDetail> orderDetails = new ArrayList<>();
		for (OrderDetailDto detailDto : orderRequestDto.getOrderDetails()) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setProductId(detailDto.getProductId());
			detail.setQuantity(detailDto.getQuantity());
			detail.setUnitPrice(detailDto.getUnitPrice());
			orderDetails.add(detail);
		}

		orderDetailRepository.saveAll(orderDetails);

		return order;

	}

	@Override
	public Order getOrderById(Integer orderId) {
		return orderRepository.findById(orderId)
				.orElseThrow(() -> new ApiException(404, "Order not found with ID: " + orderId));
	}

	@Override
	public List<Order> findByCustomerId(Integer customerId) {
		return orderRepository.findByCustomerId(customerId);
	}

	@Override
	public void updateOrderStatus(PaymentQueueDto paymentQueue) {
		// find order by id
		Order order = orderRepository.findById(paymentQueue.getOrderId())
				.orElseThrow(() -> new ApiException(404, "Order not found with ID: " + paymentQueue));
		order.setStatus("Paid");
		orderRepository.save(order);
	}
}
