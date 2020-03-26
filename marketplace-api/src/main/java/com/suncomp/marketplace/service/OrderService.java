package com.suncomp.marketplace.service;

import org.springframework.stereotype.Service;

import com.suncomp.marketplace.dao.OrderDAO;
import com.suncomp.marketplace.entity.MPOrder;

@Service
public class OrderService {

	private OrderDAO orderDAO;
	
	public MPOrder submitOrder(MPOrder order) {
		return orderDAO.save(order);
	}
}
