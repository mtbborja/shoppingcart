package com.shoppingcart.productservice.dto;

public class RatingDto {

	private Double rate;
	private Integer count;

	public RatingDto() {
	}

	public RatingDto(Double rate, Integer count) {
		this.rate = rate;
		this.count = count;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}