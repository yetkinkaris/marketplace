package com.suncomp.marketplace.service;

import java.util.Date;
import java.util.List;

import com.suncomp.marketplace.dao.ProductDAO;
import com.suncomp.marketplace.entity.Product;

public class ProductService {
	
	private ProductDAO productDAO;

	public Product save(Product product) {
		return productDAO.save(product);
	}
	
	public List<Product> retrieveAll(){
		return productDAO.findByValidFromBeforeAndValidToAfter(new Date());
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

}
