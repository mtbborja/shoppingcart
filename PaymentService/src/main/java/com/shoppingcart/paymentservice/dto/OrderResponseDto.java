package com.shoppingcart.paymentservice.dto;

public class OrderResponseDto {
	private int code;
	private String message;
	private OrderDto data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OrderDto getData() {
		return data;
	}

	public void setData(OrderDto data) {
		this.data = data;
	}

}
