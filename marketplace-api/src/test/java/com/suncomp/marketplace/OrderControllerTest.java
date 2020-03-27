package com.suncomp.marketplace;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suncomp.marketplace.dto.OrderDTO;
import com.suncomp.marketplace.dto.ProductDTO;
import com.suncomp.marketplace.entity.MPOrder;
import com.suncomp.marketplace.model.Currency;
import com.suncomp.marketplace.model.Money;
import com.suncomp.marketplace.service.impl.OrderService;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OrderService mockService;
	
	@Test
	public void whenSubmit_thenCheckPriceAndProducts() throws JsonProcessingException, Exception {
		MPOrder order = new MPOrder();
		order.setId(1L);
		when(mockService.submitOrder(any(MPOrder.class))).thenReturn(order);
		mockMvc.perform(post("/orders/submit")
		.content(objectMapper.writeValueAsString(testOrderDTO()))
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").exists());
	}
	
	@Test
	public void whenRetrieveOrders_thenReturnOrders() throws Exception {
		MPOrder order = new MPOrder();
		order.setId(1L);
		when(mockService.retrieveOrders(any(Date.class), any(Date.class))).thenReturn(Arrays.asList(order));
		mockMvc.perform(get("/orders?from=2010-12-12&to=2020-12-12"))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$", hasSize(1)))
						.andExpect(jsonPath("$[0].id", is(1)));
	}
	
	@Test
	public void whenRetrieveOrderById_thenReturnOrder() throws Exception {
		MPOrder order = new MPOrder();
		order.setId(1L);
		when(mockService.findById(1L)).thenReturn(Optional.of(order));
		mockMvc.perform(get("/orders/1"))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.id", is(1)));
	}
	
	@Test
	public void whenRetrieveOrderByBuyer_thenReturnOrders() throws Exception {
		MPOrder order = new MPOrder();
		order.setId(1L);
		when(mockService.findByBuyer(any(String.class))).thenReturn(Arrays.asList(order));
		mockMvc.perform(get("/orders/buyer/realBuyer@email.com"))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$", hasSize(1)))
						.andExpect(jsonPath("$[0].id", is(1)));
	}
	
	public static OrderDTO testOrderDTO() {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerEmail("realBuyer@email.com");
		ProductDTO product1 = new ProductDTO();
		product1.setId(1L);
		product1.setPrice(new Money(new BigDecimal(10),Currency.EUR));
		ProductDTO product2 = new ProductDTO();
		product2.setId(2L);
		product2.setPrice(new Money(new BigDecimal(10),Currency.EUR));
		orderDTO.setProducts(Arrays.asList(product1, product2));
		return orderDTO;
	}
	
}
