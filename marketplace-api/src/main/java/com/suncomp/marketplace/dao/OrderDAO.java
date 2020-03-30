package com.suncomp.marketplace.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suncomp.marketplace.entity.MPOrder;

public interface OrderDAO extends JpaRepository<MPOrder, Long>{
	List<MPOrder> findByBuyerEmail(String buyer);
	List<MPOrder> findByCreateTimeBetween(Date from, Date to);
}
