package com.shoppingcart.productservice.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.shoppingcart.productservice.dto.CategoryDto;
import com.shoppingcart.productservice.dto.ProductDto;

@Service
public class FetchServiceImpl implements IFetchService {

	@Value("${external.api.url}")
	private String externalApiUrl;

	private final RestTemplate restTemplate;

	public FetchServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		try {
			String url = externalApiUrl + "products/";
			ProductDto[] products = restTemplate.getForObject(url, ProductDto[].class);
			// Convert array to list
			return Arrays.asList(products);
		} catch (RestClientException e) {
			throw new RuntimeException("Error fetching Products from external API", e);
		}
	}

	@Override
	public List<ProductDto> getLimitedProducts(Integer limit) {
		try {
			// Build the URL with the `limit` parameter
			String url = UriComponentsBuilder.fromUriString(externalApiUrl).pathSegment("products")
					.queryParam("limit", limit).toUriString();

			ProductDto[] products = restTemplate.getForObject(url, ProductDto[].class);

			// Convert array to list
			return Arrays.asList(products);
		} catch (RestClientException e) {
			throw new RuntimeException("Error fetching Products from external API", e);
		}

	}

	@Override
	public ProductDto getProductById(Long productId) {
		try {
			String url = externalApiUrl + "products/" + productId;
			ProductDto product = restTemplate.getForObject(url, ProductDto.class);
			return product;
		} catch (RestClientException e) {
			throw new RuntimeException("Error fetching Product from external API", e);
		}

	}

	@Override
	public List<CategoryDto> getCategories() {
		try {
			String url = externalApiUrl + "products/categories";
			CategoryDto[] categories = restTemplate.getForObject(url, CategoryDto[].class);
			return Arrays.asList(categories);
		} catch (RestClientException e) {
			throw new RuntimeException("Error fetching Categories from external API", e);
		}
	}

	@Override
	public List<ProductDto> getProductsByCategory(String category) {
		try {
			String url = externalApiUrl + "products/category/" + category;
			ProductDto[] products = restTemplate.getForObject(url, ProductDto[].class);
			return Arrays.asList(products);
		} catch (RestClientException e) {
			throw new RuntimeException("Error fetching Products by category from external API", e);
		}
	}
}
