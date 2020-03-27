package com.suncomp.marketplace;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.suncomp.marketplace.entity.Product;
import com.suncomp.marketplace.model.Currency;
import com.suncomp.marketplace.model.Money;
import com.suncomp.marketplace.service.impl.ProductService;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService productService;
	
	@Test
	public void whenRetrieveAll_thenReturnProductList() {
		Product product = testProduct();
		productService.save(product);
		List<Product> products = productService.retrieveAll();
		assertThat(products).isNotEmpty();
	}
	
	@Test
	public void whenCreateProduct_thenNewProductReturn() {
		Product product = testProduct();
		product = productService.save(product);
		product = productService.findById(product.getId()).get();
		assertThat(product).isNotNull();
	}
	
	@Test
	public void whenUpdateProduct_thenNewProductReturn() {
		Product product = testProduct();
		product.setName("WillBeUpdated");
		product = productService.save(product);
		product.setName("The other product");
		productService.update(product);
		List<Product> products = productService.retrieveAll();
		assertThat(products.stream().filter(x -> x.getName().equals("The other product")).collect(Collectors.toList())).isNotEmpty();
		assertThat(products.stream().filter(x -> x.getName().equals("WillBeUpdated")).collect(Collectors.toList())).isEmpty();
	}

	@Test
	public void whenDeleteProduct_thenNotExistsInList() {
		Product product = testProduct();
		product.setName("ThatWillBeDeleted");
		product = productService.save(product);
		productService.delete(product.getId());
		List<Product> products = productService.retrieveAll();
		assertThat(products.stream().filter(x -> x.getName().equals("ThatWillBeDeleted")).collect(Collectors.toList())).isEmpty();
		assertThat(product).isNotNull();
	}
	
	private Product testProduct() {
		Product product = new Product();
		product.setName("FirstProduct");
		product.setPrice(new Money(new BigDecimal(12.12), Currency.EUR));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		product.setValidFrom(calendar.getTime());
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1);
		product.setValidTo(calendar.getTime());
		return product;
	}
}
