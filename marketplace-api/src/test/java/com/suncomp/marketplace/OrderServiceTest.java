package com.suncomp.marketplace;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.suncomp.marketplace.entity.MPOrder;
import com.suncomp.marketplace.entity.Product;
import com.suncomp.marketplace.model.Currency;
import com.suncomp.marketplace.model.Money;
import com.suncomp.marketplace.service.impl.OrderService;
import com.suncomp.marketplace.service.impl.ProductService;

@SpringBootTest
public class OrderServiceTest {

	@Autowired 
	private OrderService orderService;
	
	@Autowired 
	private ProductService productService;
	
	
	@Test
	public void whenCreateProduct_thenNewOrder() {
		MPOrder order = testOrder();
		order = orderService.submitOrder(order);
		assertThat(order).isNotNull();
	}
	
	

	@Test
	public void whenRetrieveOrders_thenReturnOrderList() {
		MPOrder order = testOrder();
		order = orderService.submitOrder(order);
		List<MPOrder> orders = orderService.retrieveOrders(addNow(-20), addNow(1));
		List<MPOrder> futureOrders = orderService.retrieveOrders(addNow(0), addNow(1));
		assertThat(orders).isNotEmpty();
		assertThat(futureOrders).isEmpty();
	}
	
	@Test
	public void whenFindById_thenReturnOrder() {
		MPOrder order = testOrder();
		order = orderService.submitOrder(order);
		order = orderService.findById(order.getId()).get();
		assertThat(order).isNotNull();
	}
	
	@Test
	public void whenFindByBuyer_thenReturnOrderList() {
		MPOrder order = testOrder();
		order = orderService.submitOrder(order);
		List<MPOrder> orders = orderService.findByBuyer("buyer1@email.com");
		assertThat(orders).isNotEmpty();
	}
	
	private MPOrder testOrder() {
		MPOrder mpOrder = new MPOrder();
		mpOrder.setBuyerEmail("buyer1@email.com");
		mpOrder.setCreateTime(addNow(-10));
		mpOrder.setId(1L);
		productService.save(testProduct());
		productService.save(testProduct());
		Product product1 = new Product();
		product1.setId(1L);
		product1.setPrice(new Money(new BigDecimal(10),Currency.EUR));
		Product product2 = new Product();
		product2.setId(2L);
		product2.setPrice(new Money(new BigDecimal(10),Currency.EUR));
		mpOrder.setProducts(Arrays.asList(product1, product2));
		return mpOrder;
	}
	
	private static Product testProduct() {
		Product product = new Product();
		product.setName("FirstProduct");
		product.setPrice(new Money(new BigDecimal(12.12), Currency.EUR));
		product.setValidFrom(addNow(-1));
		product.setValidTo(addNow(1));
		return product;
	}
	
	private static Date addNow(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
}
