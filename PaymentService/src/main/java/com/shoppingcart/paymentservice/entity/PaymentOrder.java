package com.shoppingcart.paymentservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "payment_orders")
public class PaymentOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paymentId;

	@Column(name = "order_id", nullable = false)
	@NotNull(message = "Order ID is required")
	@Positive(message = "Order ID must be a positive number")
	private Integer orderId;

	@Column(nullable = false, precision = 10, scale = 2)
	@NotNull(message = "Amount is required")
	@DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than 0")
	private BigDecimal amount;

	@Column(nullable = false, columnDefinition = "ENUM('Card', 'Cash', 'Transfer')")
	@NotNull(message = "Payment method is required")
	@Pattern(regexp = "Card|Cash|Transfer", message = "Payment method must be 'Card', 'Cash', or 'Transfer'")
	private String paymentMethod;

	@Column(name = "payment_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime paymentDate;

	@Column(nullable = false, columnDefinition = "ENUM('Successful', 'Failed') DEFAULT 'Successful'")	
	private String status = "Successful";

	// Getters and Setters

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
