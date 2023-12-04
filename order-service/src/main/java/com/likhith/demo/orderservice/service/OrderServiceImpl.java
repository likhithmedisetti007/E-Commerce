package com.likhith.demo.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.likhith.demo.orderservice.document.Order;
import com.likhith.demo.orderservice.repository.OrderRepository;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public List<Order> getOrderDetails(String customerId) {

		List<Order> orders = orderRepository.findByCustomerId(customerId);
		return orders;
	}

}