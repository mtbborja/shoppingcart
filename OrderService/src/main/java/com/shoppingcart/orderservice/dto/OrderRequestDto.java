package com.shoppingcart.orderservice.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class OrderRequestDto {

	@NotNull(message = "Customer ID is required")
	@Positive(message = "Customer ID must be a positive number")
	private Integer customerId;

	@NotNull(message = "Order details are required")
	@Size(min = 1, message = "Order must contain at least one detail")
	private List<OrderDetailDto> orderDetails;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public List<OrderDetailDto> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailDto> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
