package com.shoppingcart.paymentservice.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shoppingcart.paymentservice.dto.PaymentQueueDto;

@Service
public class MessagePublisherServiceImpl implements MessagePublisherService {

	@Value("${shoppingcart.queue.name}")
	private String queueNamePayments;

	private final RabbitTemplate rabbitTemplate;

	public MessagePublisherServiceImpl(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void publishToPayments(PaymentQueueDto message) {
		rabbitTemplate.convertAndSend(queueNamePayments, message);
	}
}
