package com.suncomp.marketplace;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suncomp.marketplace.dto.ProductDTO;
import com.suncomp.marketplace.entity.Product;
import com.suncomp.marketplace.model.Currency;
import com.suncomp.marketplace.model.Money;
import com.suncomp.marketplace.service.impl.ProductService;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private ProductService mockService;
	
	@Test
	public void whenCreate_thenReturnProduct() throws JsonProcessingException, Exception {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName("New Product");
		productDTO.setPrice(new Money(new BigDecimal(10), Currency.EUR));
		Product product = new Product();
		product.setId(1L);
		when(mockService.save(any(Product.class))).thenReturn(product);
		mockMvc.perform(post("/products/create")
						.content(objectMapper.writeValueAsString(productDTO))
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.id").exists());
	}
	
	@Test
	public void whenRetrieveAll_thenReturnProductList() throws Exception {
		Product product = new Product();
		product.setId(1L);
		when(mockService.retrieveAll()).thenReturn(Arrays.asList(product));
		mockMvc.perform(get("/products"))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$", hasSize(1)))
						.andExpect(jsonPath("$[0].id", is(1)));
	}
	
	@Test
	public void whenRetrieveById_thenReturnProduct() throws Exception {
		Product product = new Product();
		product.setId(1L);
		when(mockService.findById(1L)).thenReturn(Optional.of(product));
		mockMvc.perform(get("/products/1"))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.id", is(1)));
	}
	
	@Test
	public void whenUpdate_thenReturnNewProduct() throws JsonProcessingException, Exception {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName("Updated Product");
		productDTO.setPrice(new Money(new BigDecimal(10), Currency.EUR));
		productDTO.setId(1L);
		Product product = new Product();
		product.setId(1L);
		when(mockService.findById(1L)).thenReturn(Optional.of(product));
		when(mockService.update(any(Product.class))).thenReturn(product);
		mockMvc.perform(put("/products")
						.content(objectMapper.writeValueAsString(productDTO))
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.id").exists());
	}
	
	@Test
	public void whenDelete_thenReturnOk() throws Exception {
		Product product = new Product();
		product.setId(1L);
		when(mockService.findById(1L)).thenReturn(Optional.of(product));
		mockMvc.perform(delete("/products/1"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void whenDeleteNotExist_thenReturnBadRequest() throws Exception {
		mockMvc.perform(delete("/products/1"))
		.andExpect(status().isBadRequest());
	}
}
