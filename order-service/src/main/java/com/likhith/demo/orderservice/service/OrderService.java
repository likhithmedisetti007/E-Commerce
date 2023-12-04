package com.likhith.demo.orderservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.likhith.demo.orderservice.document.Order;

@Service
public interface OrderService {

	public List<Order> getOrderDetails(String customerId);

}