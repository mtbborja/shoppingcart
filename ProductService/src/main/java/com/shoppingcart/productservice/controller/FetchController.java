package com.shoppingcart.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.productservice.service.IFetchService;

@RestController
@RequestMapping("/products")
public class FetchController {

	private final IFetchService fetchService;

	// public FetchController(@Qualifier("fetchServiceImpl") IFetchService
	// productService) {
	public FetchController(IFetchService productService) {
		this.fetchService = productService;
	}

	@GetMapping
	public ResponseEntity<?> getAllProducts() {
		return ResponseEntity.ok(fetchService.getAllProducts());
	}

	@GetMapping("/limited")
	public ResponseEntity<?> getLimitedProducts(@RequestParam("limit") Integer limit) {
		return ResponseEntity.ok(fetchService.getLimitedProducts(limit));
	}

	@GetMapping("/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable("productId") Long productId) {
		return ResponseEntity.ok(fetchService.getProductById(productId));
	}

	@GetMapping("/categories")
	public ResponseEntity<?> getAllCategories() {
		return ResponseEntity.ok(fetchService.getCategories());
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<?> getProductsByCategory(@PathVariable("category") String category) {
		return ResponseEntity.ok(fetchService.getProductsByCategory(category));
	}
}
