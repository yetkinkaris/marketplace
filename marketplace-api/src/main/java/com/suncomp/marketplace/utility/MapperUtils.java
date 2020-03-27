package com.suncomp.marketplace.utility;

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
		BeanUtils.copyProperties(orderDTO, order, "price");
		return order;
	}
	
	public static OrderDTO convertToDTO(MPOrder order) {
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(order, orderDTO);
		return orderDTO;
	}
}
