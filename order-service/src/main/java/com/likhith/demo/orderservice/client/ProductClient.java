package com.likhith.demo.orderservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.likhith.demo.orderservice.dto.Product;

@FeignClient(name = "productClient", url = "http://localhost:9002/product")
public interface ProductClient {

	@GetMapping("/getProductDetails/{productIds}")
	public List<Product> getProductDetails(@PathVariable("productIds") List<String> productIds);

	@PostMapping("/addProductDetails")
	public List<String> addProductDetails(@RequestBody List<Product> products);

	@GetMapping("/checkProductExists")
	public String checkProductExists(@RequestParam("name") String name, @RequestParam("cost") String cost,
			@RequestParam("category") String category);

}