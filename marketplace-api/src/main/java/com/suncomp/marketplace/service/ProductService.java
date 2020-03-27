package com.suncomp.marketplace.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suncomp.marketplace.dao.ProductDAO;
import com.suncomp.marketplace.entity.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;

	public Product save(Product product) {
		return productDAO.save(product);
	}
	
	public List<Product> retrieveAll(){
		Date now = new Date();
		return productDAO.findByValidFromBeforeAndValidToAfter(now, now);
	}
	
	public Product update(Product product) {
		Product oldProduct = productDAO.getOne(product.getId());
		Date now = new Date();
		oldProduct.setValidTo(now);
		product.setId(null);
		productDAO.save(oldProduct);
		product.setValidFrom(now);
		return productDAO.save(product);
	}

	public Optional<Product> findById(Long id) {
		return productDAO.findById(id);
	}

	public void delete(Long id) {
		Product product = productDAO.getOne(id);
		product.setValidTo(new Date());
		productDAO.save(product);
	}

}
