package com.suncomp.marketplace.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suncomp.marketplace.dto.OrderDTO;
import com.suncomp.marketplace.dto.ProductDTO;
import com.suncomp.marketplace.entity.MPOrder;
import com.suncomp.marketplace.entity.Product;
import com.suncomp.marketplace.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private OrderService orderService;
	
	@PostMapping("/submit")
	public ResponseEntity<OrderDTO> submit(OrderDTO order) {
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/retrieveAll")
	public ResponseEntity<List<OrderDTO>> retrieveOrders() {
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/retrieveById/{id}")
	public ResponseEntity<OrderDTO> retrieveOrderWithId(@PathVariable("id") long id) {
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/retrieveByBuyer/{buyer}")
	public ResponseEntity<List<OrderDTO>> retrieveOrderWithBuyer(@PathVariable("buyer") String id) {
		return ResponseEntity.ok().build();
	}
	
	private MPOrder convertToEntity(OrderDTO orderDTO) {
		MPOrder order = new MPOrder();
		BeanUtils.copyProperties(orderDTO, order);
		return order;
	}
	
	private OrderDTO convertToDTO(MPOrder order) {
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(order, orderDTO);
		return orderDTO;
	}
	
}
