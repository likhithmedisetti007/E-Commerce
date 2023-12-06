package com.likhith.demo.customerservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.likhith.demo.customerservice.dto.Order;

@FeignClient(name = "orderClient", url = "http://localhost:9001/order")
public interface OrderClient {

	@GetMapping("/getOrderDetails/{ids}")
	public List<Order> getOrderDetails(@PathVariable("ids") List<String> ids);

	@PostMapping("/addOrderDetails")
	public List<String> addOrderDetails(@RequestBody List<Order> orders);

	@PutMapping("/updateOrderDetails/{id}")
	public Boolean updateOrderDetails(@RequestBody Order order, @PathVariable("id") String id);

	@DeleteMapping("/deleteOrderDetails/{id}")
	public String deleteOrderDetails(@PathVariable("id") String id);

	@GetMapping("/checkOrderExists/{id}")
	public Boolean checkOrderExists(@PathVariable("id") String id);

}