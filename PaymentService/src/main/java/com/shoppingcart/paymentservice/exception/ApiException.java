package com.shoppingcart.paymentservice.exception;

public class ApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final Integer errorCode;
	
	public ApiException(int errorCode) {
	    super("Error with code: " + errorCode);
	    this.errorCode = errorCode;
	}

	public ApiException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}
}
