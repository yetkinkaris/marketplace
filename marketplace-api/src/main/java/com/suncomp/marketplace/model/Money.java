package com.suncomp.marketplace.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Money {

	private BigDecimal amount;
	
	private Currency currency;

	public Money() {
	}

	public Money(BigDecimal amount, Currency currency) {
		super();
		this.amount = amount;
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}
