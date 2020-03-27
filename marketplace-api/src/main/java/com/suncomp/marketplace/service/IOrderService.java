package com.suncomp.marketplace.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.suncomp.marketplace.entity.MPOrder;

public interface IOrderService {

	MPOrder submitOrder(MPOrder order);

	List<MPOrder> retrieveOrders(Date from, Date to);

	Optional<MPOrder> findById(long id);

	List<MPOrder> findByBuyer(String buyer);

}
