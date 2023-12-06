package com.likhith.demo.orderservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.likhith.demo.orderservice.document.Order;

@Service
public interface OrderService {

	public List<Order> getOrderDetails(List<String> ids);

	public List<String> addOrderDetails(List<Order> orders);

	public Boolean updateOrderDetails(Order order, String id);

	public Boolean deleteOrderDetails(String id);

	public Boolean checkOrderExists(String id);

}