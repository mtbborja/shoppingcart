package com.shoppingcart.paymentservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shoppingcart.paymentservice.dto.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse<Object>> handleApiException(ApiException ex) {
		ApiResponse<Object> response = new ApiResponse<>(ex.getErrorCode(), ex.getMessage(), null);
		return ResponseEntity.status(ex.getErrorCode()).body(response);
	}
}
