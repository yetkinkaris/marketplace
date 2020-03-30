package com.suncomp.marketplace.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.suncomp.marketplace.dao.ProductDAO;
import com.suncomp.marketplace.entity.Product;
import com.suncomp.marketplace.service.IProductService;

@Service
public class ProductService implements IProductService{
	
	public ProductService(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	private ProductDAO productDAO;

	@Override
	public Product save(Product product) {
		if (Objects.isNull(product.getId())) {
			product.setCreateTime(new Date());
			product.setValidFrom(new Date());
			Calendar calendar = Calendar.getInstance();
			calendar.set(2999, 11, 11);
			product.setValidTo(calendar.getTime());
		}
		return productDAO.save(product);
	}
	
	@Override
	public List<Product> retrieveAll(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY,23);
		return productDAO.findByValidFromBeforeAndValidToAfter(calendar.getTime(), calendar.getTime());
	}
	
	@Override
	public Product update(Product product) {
		Product oldProduct = productDAO.findById(product.getId()).get();
		oldProduct.setValidTo(new Date());
		productDAO.save(oldProduct);
		product.setId(null);
		if	(product.getName() == null) {
			product.setName(oldProduct.getName());
		}
		if	(product.getPrice() == null) {
			product.setPrice(oldProduct.getPrice());
		}
		return save(product);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productDAO.findById(id);
	}

	@Override
	public void delete(Long id) {
		Product product = productDAO.findById(id).get();
		product.setValidTo(new Date());
		productDAO.save(product);
	}

}
