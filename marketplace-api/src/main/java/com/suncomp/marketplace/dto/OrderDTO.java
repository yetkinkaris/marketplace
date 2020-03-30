package com.suncomp.marketplace.dto;

import java.util.List;

import javax.validation.constraints.Email;

import com.suncomp.marketplace.model.Money;

public class OrderDTO {

	private Long id;
	
	private Money price;
	
	@Email (message = "Email should be valid")
	private String buyerEmail;

	private List<ProductDTO> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Money getPrice() {
		return price;
	}

	public void setPrice(Money price) {
		this.price = price;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
}
