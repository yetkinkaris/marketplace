package com.suncomp.marketplace.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.util.CollectionUtils;

import com.suncomp.marketplace.model.Money;

@Entity
public class MPOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date createTime;
	
	@Transient
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
		if (CollectionUtils.isEmpty(this.products)) {
			price = new Money(this.products.stream().map(product -> product.getPrice().getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add), products.get(0).getPrice().getCurrency());
		}
	}
	
}
