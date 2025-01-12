package com.shoppingcart.paymentservice.service;

import java.util.List;

import com.shoppingcart.paymentservice.entity.PaymentOrder;

public interface PaymentOrderService {

	public PaymentOrder processPayment(PaymentOrder paymentOrder);
	
	public List<PaymentOrder> getPaymentsByStatus(String status);

}
