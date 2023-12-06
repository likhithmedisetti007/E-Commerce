package com.likhith.demo.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.demo.productservice.document.Product;
import com.likhith.demo.productservice.service.ProductService;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/getProductDetails/{productIds}")
	public ResponseEntity<?> getProductDetails(@PathVariable("productIds") List<String> productIds) {
		List<Product> products = productService.getProductDetails(productIds);
		if (CollectionUtils.isEmpty(products)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(products);
	}

	@PostMapping("/addProductDetails")
	public ResponseEntity<?> addProductDetails(@RequestBody List<Product> products) {
		List<String> productIds = productService.addProductDetails(products);
		if (!CollectionUtils.isEmpty(productIds)) {
			return ResponseEntity.ok().body(productIds);
		}
		return ResponseEntity.internalServerError().body("Product Details Not Added");
	}

	@GetMapping("/checkProductExists")
	public ResponseEntity<?> checkProductExists(@RequestParam("name") String name, @RequestParam("cost") String cost,
			@RequestParam("category") String category) {
		String productId = productService.checkProductExists(name, cost, category);
		if (StringUtils.isNotEmpty(productId)) {
			return ResponseEntity.ok().body(productId);
		}
		return ResponseEntity.ok().body(null);
	}

}