package com.suncomp.marketplace.dto;

import com.suncomp.marketplace.model.Money;

public class ProductDTO {

	private Long id;
	
	private String name;
	
	private Money price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Money getPrice() {
		return price;
	}

	public void setPrice(Money price) {
		this.price = price;
	}
}
