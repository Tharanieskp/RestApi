package com.springboot.restapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.exception.ResourceNotFoundException;
import com.springboot.restapi.model.Product;
import com.springboot.restapi.repository.ProductRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	//get products
	@GetMapping("projects")
	public List<Product> getAllProduct(){
		return this.productRepository.findAll();
	}
	
	//get product by id 
	@GetMapping("/itemById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
	        throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found for this id ::"+ productId));
		return ResponseEntity.ok().body(product);
	}
	
	//get product by name 
	@GetMapping("/itemByName/{productName}")
	public ResponseEntity<Product> getProductByName(@PathVariable(value = "productName") String name)
	        throws ResourceNotFoundException {
		Product product = ((Optional<Product>) productRepository.findByProductName(name))
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found for this name ::"+ name));
		return ResponseEntity.ok().body(product);
	}
	
	
	
	//save product 
	@PostMapping("projects")
	public Product createProduct(@RequestBody Product product) {
		return this.productRepository.save(product);
	}
	
	//update product
	@PutMapping("/itemById/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId,
			@Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + productId));

	
		product.setProductName(productDetails.getProductName());
		product.setBrand(productDetails.getBrand());
		product.setDescription(productDetails.getDescription());
		product.setImageUrl(productDetails.getImageUrl());
		product.setPrice(productDetails.getPrice());
		product.setPriceCurrency(productDetails.getPriceCurrency());
		final Product updatedProduct = productRepository.save(product);
		return ResponseEntity.ok(updatedProduct);
	}
	
	//delete product
	@DeleteMapping("/itemById/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId)
			throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + productId));

		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
