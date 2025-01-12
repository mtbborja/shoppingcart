package com.shoppingcart.paymentservice.service;

import com.shoppingcart.paymentservice.dto.PaymentQueueDto;

public interface MessagePublisherService {

	public void publishToPayments(PaymentQueueDto message);

}
