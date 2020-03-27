package com.suncomp.marketplace.entity.listener;

import java.math.BigDecimal;

import javax.persistence.PostLoad;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.suncomp.marketplace.entity.MPOrder;
import com.suncomp.marketplace.model.Money;

@Component
public class MPOrderListener {
	@PostLoad
	public void postLoad(MPOrder order) {
		
		if (!CollectionUtils.isEmpty(order.getProducts())) {
			order.setPrice(new Money(order.getProducts().stream().filter(product -> product.getPrice() != null).map(product -> product.getPrice().getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add), order.getProducts().get(0).getPrice().getCurrency()));
		}
	}
}
