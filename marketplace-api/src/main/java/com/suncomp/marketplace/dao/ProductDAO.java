package com.suncomp.marketplace.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suncomp.marketplace.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Long>{
	List<Product> findByValidFromBeforeAndValidToAfter(Date effectiveDate, Date same);
}
