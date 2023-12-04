package com.likhith.demo.customerservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.likhith.demo.customerservice.dto.Product;

@FeignClient(name = "productClient", url = "http://localhost:9002/product")
public interface ProductClient {

	@GetMapping("/getProductDetails/{productIds}")
	public List<Product> getProductDetails(@PathVariable("productIds") List<String> productIds);

}