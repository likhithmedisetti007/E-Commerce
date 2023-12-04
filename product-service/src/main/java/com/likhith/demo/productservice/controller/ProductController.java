package com.likhith.demo.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.demo.productservice.document.Product;
import com.likhith.demo.productservice.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/getProductDetails/{productIds}")
	public ResponseEntity<?> getProductDetails(@PathVariable("productIds") List<String> productIds) {
		List<Product> products = productService.getProductDetails(productIds);
		return ResponseEntity.ok(products);
	}

}