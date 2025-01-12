package com.shoppingcart.orderservice.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.shoppingcart.orderservice.dto.PaymentQueueDto;
import com.shoppingcart.orderservice.service.OrderService;

@Component
@EnableScheduling
public class Consumer {

	private final OrderService orderService;

	public Consumer(OrderService orderService) {
		this.orderService = orderService;
	}

	// Method to listen to messages from the queue
	@RabbitListener(queues = { "${shoppingcart.queue.name}" })
	public void receive(@Payload PaymentQueueDto message) {
		// Proccess message
		System.out.println("received message: ");
		orderService.updateOrderStatus(message);
	}

}
