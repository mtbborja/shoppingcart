package com.shoppingcart.orderservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.orderservice.dto.ApiResponse;
import com.shoppingcart.orderservice.dto.OrderRequestDto;
import com.shoppingcart.orderservice.entity.Order;
import com.shoppingcart.orderservice.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * Endpoint to create a new order. Accepts an order request with order details,
	 * processes it, and returns the saved order.
	 * 
	 * @param orderRequestDto the order request containing customer and order
	 *                        details.
	 * @return the created order as a ResponseEntity with HTTP status 200 (OK).
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<Order>> createOrder(@Valid @RequestBody OrderRequestDto orderRequest) {
		Order createdOrder = orderService.saveOrder(orderRequest);
		ApiResponse<Order> response = new ApiResponse<>(201, "Order created successfully", createdOrder);
		return ResponseEntity.status(201).body(response);
	}

	/**
	 * Endpoint to retrieve an order by its ID. Fetches the order from the database
	 * based on the provided ID and returns it.
	 * 
	 * @param id the ID of the order to retrieve.
	 * @return the order entity as a ResponseEntity with HTTP status 200 (OK).
	 */
	@GetMapping("/{orderId}")
	public ResponseEntity<ApiResponse<Order>> getOrderById(@PathVariable Integer orderId) {
		Order order = orderService.getOrderById(orderId);
		ApiResponse<Order> response = new ApiResponse<>(200, "Order retrieved successfully", order);
		return ResponseEntity.ok(response);
	}

	/**
	 * Endpoint to retrieve orders by customer id
	 * 
	 * @param id the customerId.
	 * @return the order list as a ResponseEntity with HTTP status 200 (OK).
	 */
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<ApiResponse<List<Order>>> getOrderByCustomerId(
			@PathVariable("customerId") Integer customerId) {
		List<Order> orders = orderService.findByCustomerId(customerId);

		if (orders == null || orders.isEmpty()) {
			// If no orders found, return 404 Not Found with a proper message
			ApiResponse<List<Order>> response = new ApiResponse<>(404, "No orders found for customer ID: " + customerId,
					null);
			return ResponseEntity.status(404).body(response);
		}

		// If orders are found, return 200 OK with the list of orders
		ApiResponse<List<Order>> response = new ApiResponse<>(200, "Orders retrieved successfully", orders);
		return ResponseEntity.ok(response);
	}
}
