package com.suncomp.marketplace.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.suncomp.marketplace.dao.OrderDAO;
import com.suncomp.marketplace.entity.MPOrder;
import com.suncomp.marketplace.service.IOrderService;

@Service
public class OrderService implements IOrderService{

	public OrderService(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	private OrderDAO orderDAO;
	
	@Override
	public MPOrder submitOrder(MPOrder order) {
		return orderDAO.save(order);
	}

	@Override
	public List<MPOrder> retrieveOrders(Date from, Date to) {
		return orderDAO.findByCreateTimeBetween(from, to);
	}

	@Override
	public Optional<MPOrder> findById(long id) {
		return orderDAO.findById(id);
	}
	
	@Override
	public List<MPOrder> findByBuyer(String buyer) {
		return orderDAO.findByBuyer(buyer);
	}
}
