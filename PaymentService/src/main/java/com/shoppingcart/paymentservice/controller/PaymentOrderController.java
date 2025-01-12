package com.shoppingcart.paymentservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.paymentservice.dto.ApiResponse;
import com.shoppingcart.paymentservice.entity.PaymentOrder;
import com.shoppingcart.paymentservice.service.PaymentOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentOrderController {

	private final PaymentOrderService paymentOrderService;

	public PaymentOrderController(PaymentOrderService paymentOrderService) {
		this.paymentOrderService = paymentOrderService;
	}

	/**
	 * Endpoint to process a new payment.
	 *
	 * @param paymentOrder the payment details.
	 * @return the processed payment.
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<PaymentOrder>> processPayment(@Valid @RequestBody PaymentOrder paymentOrder) {
		PaymentOrder processedPayment = paymentOrderService.processPayment(paymentOrder);
		ApiResponse<PaymentOrder> response = new ApiResponse<>(201, "Payment processed successfully", processedPayment);
		return ResponseEntity.status(201).body(response);
	}

	/**
	 * Endpoint to get all payments by their status.
	 *
	 * @param status the status of the payment (e.g., "Successful", "Failed").
	 * @return a list of payments with the given status.
	 */
	@GetMapping("/status/{status}")
	public ResponseEntity<ApiResponse<List<PaymentOrder>>> getPaymentsByStatus(@PathVariable String status) {
		List<PaymentOrder> payments = paymentOrderService.getPaymentsByStatus(status);
		ApiResponse<List<PaymentOrder>> response = new ApiResponse<>(200, "Payments retrieved successfully", payments);
		return ResponseEntity.ok(response);
	}
}
