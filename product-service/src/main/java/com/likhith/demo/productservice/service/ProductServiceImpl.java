package com.likhith.demo.productservice.service;

import java.util.List;

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

		List<Product> products = productRepository.findBy_idIn(productIds);
		return products;
	}

}