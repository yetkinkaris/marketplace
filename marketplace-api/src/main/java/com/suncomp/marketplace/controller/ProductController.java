package com.suncomp.marketplace.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suncomp.marketplace.dto.ProductDTO;
import com.suncomp.marketplace.entity.Product;
import com.suncomp.marketplace.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;
	
	public ResponseEntity<ProductDTO> create(ProductDTO productDTO) {
		Product product = null;
		try {
			product = productService.save(convertToEntity(productDTO));			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(convertToDTO(product));
	}

	private Product convertToEntity(ProductDTO productDTO) {
		Product product = new Product();
		BeanUtils.copyProperties(productDTO, product);
		return product;
	}
	
	private ProductDTO convertToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		BeanUtils.copyProperties(product, productDTO);
		return productDTO;
	}
	
}
