package com.suncomp.marketplace.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.suncomp.marketplace.model.Money;

@Entity
public class MPOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date createTime;
	
	private Money price;
	
	private String buyer;
	
	@OneToMany(targetEntity = Product.class)
	private List<Product> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Money getPrice() {
		return price;
	}

	public void setPrice(Money price) {
		this.price = price;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
