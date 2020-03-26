package com.suncomp.marketplace.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suncomp.marketplace.entity.MPOrder;

public interface OrderDAO extends JpaRepository<MPOrder, Long>{

}
