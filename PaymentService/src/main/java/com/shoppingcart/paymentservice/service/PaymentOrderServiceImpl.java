package com.shoppingcart.paymentservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shoppingcart.paymentservice.dto.OrderDto;
import com.shoppingcart.paymentservice.dto.OrderResponseDto;
import com.shoppingcart.paymentservice.dto.PaymentQueueDto;
import com.shoppingcart.paymentservice.entity.PaymentOrder;
import com.shoppingcart.paymentservice.exception.ApiException;
import com.shoppingcart.paymentservice.repository.PaymentOrderRepository;

@Service
public class PaymentOrderServiceImpl implements PaymentOrderService {

	@Value("${orderservice.api.url}")
	private String orderServiceApiUrl;

	private final RestTemplate restTemplate;

	private final MessagePublisherServiceImpl publisherService;

	private final PaymentOrderRepository paymentOrderRepository;

	public PaymentOrderServiceImpl(RestTemplate restTemplate, MessagePublisherServiceImpl publisherService,
			PaymentOrderRepository paymentOrderRepository) {
		this.restTemplate = restTemplate;
		this.paymentOrderRepository = paymentOrderRepository;
		this.publisherService = publisherService;

	}

	@Override
	public PaymentOrder processPayment(PaymentOrder paymentOrder) {
		try {
			// Validate order ID with OrderService
			validateOrder(paymentOrder.getOrderId());

			paymentOrder.setPaymentDate(LocalDateTime.now());
			PaymentOrder newPaymentOrder = paymentOrderRepository.save(paymentOrder);

			PaymentQueueDto queueObj = new PaymentQueueDto();
			queueObj.setOrderId(paymentOrder.getOrderId());
			// add to queue
			this.publisherService.publishToPayments(queueObj);

			return newPaymentOrder;
		} catch (ApiException ex) {
			throw ex; // Re-throw custom exception if order validation fails
		} catch (Exception e) {
			throw new ApiException(500, "Failed to process payment. " + e.getMessage());
		}
	}

	@Override
	public List<PaymentOrder> getPaymentsByStatus(String status) {
		return paymentOrderRepository.findByStatus(status);
	}

	/**
	 * Validates the existence of the order by making a call to the OrderService.
	 */
	private void validateOrder(Integer orderId) {
		String url = orderServiceApiUrl + "orders/" + orderId;
		try {
			// Perform a GET request to the OrderService
			OrderResponseDto response = restTemplate.getForObject(url, OrderResponseDto.class);

			// Validate the response code and the order status
			if (response == null || response.getCode() != 200 || response.getData() == null) {
				throw new ApiException(400, "Failed to retrieve order with ID: " + orderId);
			}

			OrderDto order = response.getData();
			if (!"Pending".equalsIgnoreCase(order.getStatus())) {
				throw new ApiException(400, "Order with ID: " + orderId + " is not in Pending status.");
			}
		} catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
			// Handle 404 error (Order not found)
			throw new ApiException(400, "Order not found with ID: " + orderId);
		} catch (org.springframework.web.client.ResourceAccessException e) {
			// Handle connection issues (OrderService is unavailable)
			throw new ApiException(503, "OrderService is currently unavailable. Please try again later.");
		} 
	}

}
