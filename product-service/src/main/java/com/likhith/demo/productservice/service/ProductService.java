package com.likhith.demo.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.likhith.demo.productservice.document.Product;

@Service
public interface ProductService {

	public List<Product> getProductDetails(List<String> productIds);

	public List<String> addProductDetails(List<Product> products);

	public String checkProductExists(String name, String cost, String category);

}