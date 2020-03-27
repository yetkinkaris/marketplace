package com.suncomp.marketplace.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suncomp.marketplace.dao.OrderDAO;
import com.suncomp.marketplace.entity.MPOrder;

@Service
public class OrderService {

	@Autowired
	private OrderDAO orderDAO;
	
	public MPOrder submitOrder(MPOrder order) {
		return orderDAO.save(order);
	}

	public List<MPOrder> retrieveOrders(Date from, Date to) {
		return orderDAO.findByCreateTimeBetween(from, to);
	}

	public Optional<MPOrder> findById(long id) {
		return orderDAO.findById(id);
	}
	
	public List<MPOrder> findByBuyer(String buyer) {
		return orderDAO.findByBuyer(buyer);
	}
}
