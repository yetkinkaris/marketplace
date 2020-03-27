package com.suncomp.marketplace.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suncomp.marketplace.dto.ProductDTO;
import com.suncomp.marketplace.entity.Product;
import com.suncomp.marketplace.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/create")
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
		Product product = null;
		try {
			product = productService.save(convertToEntity(productDTO));			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(convertToDTO(product));
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> retrieveAll(){
		List<ProductDTO> productDTOs = productService.retrieveAll().stream().map(product -> convertToDTO(product)).collect(Collectors.toList());
		return ResponseEntity.ok(productDTOs);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> retrieveById(@PathVariable Long id){
		Optional<Product> product = productService.findById(id);
		if (product.isPresent()) {
			return ResponseEntity.ok(convertToDTO(product.get()));
		}
		else
			return ResponseEntity.notFound().build();
	}
	
	@PutMapping
	public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product){
		if (!productService.findById(product.getId()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
		return ResponseEntity.ok(convertToDTO(productService.update(convertToEntity(product))));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id){
		if (!productService.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
		productService.delete(id);
		return ResponseEntity.ok().build();
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
