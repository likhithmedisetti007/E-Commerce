package com.likhith.demo.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.demo.orderservice.document.Order;
import com.likhith.demo.orderservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping("/getOrderDetails/{customerId}")
	public ResponseEntity<?> getOrderDetails(@PathVariable("customerId") String customerId) {
		List<Order> ordersList = orderService.getOrderDetails(customerId);
		return ResponseEntity.ok(ordersList);
	}

}