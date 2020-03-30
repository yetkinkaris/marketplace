package com.suncomp.marketplace.controller;

import static com.suncomp.marketplace.utility.MapperUtils.convertToDTO;
import static com.suncomp.marketplace.utility.MapperUtils.convertToEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suncomp.marketplace.dto.OrderDTO;
import com.suncomp.marketplace.entity.MPOrder;
import com.suncomp.marketplace.service.impl.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	private OrderService orderService;
	
	@PostMapping("/submit")
	public ResponseEntity<OrderDTO> submit(@RequestBody @Valid OrderDTO orderDTO) {
		orderDTO.setId(null);
		MPOrder order = orderService.submitOrder(convertToEntity(orderDTO));
		return ResponseEntity.ok(convertToDTO(order));
	}
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> retrieveOrders(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam Date from, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam Date to) {
		List<OrderDTO> orderDTOs = orderService.retrieveOrders(from, to).stream().map(order -> convertToDTO(order)).collect(Collectors.toList());
		return ResponseEntity.ok(orderDTOs);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> retrieveOrderWithId(@PathVariable("id") long id) {
		Optional<MPOrder> order = orderService.findById(id);
		if (order.isPresent()) {
			return ResponseEntity.ok(convertToDTO(order.get()));
		}
		else
			return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/buyer/{buyer}")
	public ResponseEntity<List<OrderDTO>> retrieveOrderWithBuyer(@PathVariable("buyer") String buyer) {
		List<OrderDTO> orderDTOs = orderService.findByBuyer(buyer).stream().map(order -> convertToDTO(order)).collect(Collectors.toList());
		return ResponseEntity.ok(orderDTOs);
	}
	
}
