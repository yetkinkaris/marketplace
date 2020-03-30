package com.suncomp.marketplace.utility;


import java.util.ArrayList;

import org.springframework.beans.BeanUtils;

import com.suncomp.marketplace.dto.OrderDTO;
import com.suncomp.marketplace.dto.ProductDTO;
import com.suncomp.marketplace.entity.MPOrder;
import com.suncomp.marketplace.entity.Product;

public class MapperUtils {

	public static Product convertToEntity(ProductDTO productDTO) {
		Product product = new Product();
		BeanUtils.copyProperties(productDTO, product);
		return product;
	}
	
	public static ProductDTO convertToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		BeanUtils.copyProperties(product, productDTO);
		return productDTO;
	}
	
	public static MPOrder convertToEntity(OrderDTO orderDTO) {
		MPOrder order = new MPOrder();
		BeanUtils.copyProperties(orderDTO, order, "price", "products");
		order.setProducts(new ArrayList<Product>());
		for (ProductDTO productDTO : orderDTO.getProducts()) {
			order.getProducts().add(convertToEntity(productDTO));
		}
		return order;
	}
	
	public static OrderDTO convertToDTO(MPOrder order) {
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(order, orderDTO, "products");
		orderDTO.setProducts(new ArrayList<ProductDTO>());
		for (Product product : order.getProducts()) {
			orderDTO.getProducts().add(convertToDTO(product));
		}
		return orderDTO;
	}
}
