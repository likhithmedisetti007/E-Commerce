package com.likhith.demo.productservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.likhith.demo.productservice.document.Product;
import com.likhith.demo.productservice.repository.ProductRepository;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getProductDetails(List<String> productIds) {

		List<Product> products = productRepository.findByIdIn(productIds);
		return products;
	}

	@Override
	public List<String> addProductDetails(List<Product> products) {

		List<String> productIds = new ArrayList<>();

		for (Product product : products) {
			Product productFromDB = productRepository.insert(product);
			productIds.add(productFromDB.getId());
		}

		return productIds;
	}

	@Override
	public String checkProductExists(String name, String cost, String category) {
		Optional<Product> productOptional = productRepository.findByNameAndCostAndCategory(name, cost, category);
		if (productOptional.isPresent()) {
			return productOptional.get().getId();
		}
		return null;
	}
}