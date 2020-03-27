package com.suncomp.marketplace;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.suncomp.marketplace.service.impl.OrderService;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OrderService mockService;
	
	@Test
	public void whenSubmit_thenCheckPriceAndProducts() {
		
	}
	
	@Test
	public void whenRetrieveOrders_thenReturnOrders() {
		
	}
	
	@Test
	public void whenRetrieveOrderById_thenReturnOrder() {
		
	}
	
	@Test
	public void whenRetrieveOrderByBuyer_thenReturnOrders() {
		
	}
	
}
