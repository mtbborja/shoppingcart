package com.shoppingcart.orderservice.exception;

public class ApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private Integer errorCode;

	public ApiException(int errorCode, String message) {
		super(message); 
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
}
