package com.suncomp.marketplace.service;

import java.util.List;
import java.util.Optional;

import com.suncomp.marketplace.entity.Product;

public interface IProductService {

	Product save(Product product);

	List<Product> retrieveAll();

	Product update(Product product);

	Optional<Product> findById(Long id);

	void delete(Long id);

}
