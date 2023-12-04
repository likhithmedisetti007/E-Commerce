package com.likhith.demo.customerservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.likhith.demo.customerservice.dto.Order;

@FeignClient(name = "orderClient", url = "http://localhost:9001/order")
public interface OrderClient {

	@GetMapping("/getOrderDetails/{customerId}")
	public List<Order> getOrderDetails(@PathVariable("customerId") String customerId);

}