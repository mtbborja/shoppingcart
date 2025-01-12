package com.shoppingcart.productservice.service;

import java.util.List;

import com.shoppingcart.productservice.dto.CategoryDto;
import com.shoppingcart.productservice.dto.ProductDto;

public interface IFetchService {

	public List<ProductDto> getAllProducts();
	
	public List<ProductDto> getLimitedProducts(Integer limit);

	public ProductDto getProductById(Long productId);

	public List<CategoryDto> getCategories();

	public List<ProductDto> getProductsByCategory(String category);
}
