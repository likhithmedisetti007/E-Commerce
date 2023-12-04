package com.likhith.demo.orderservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.likhith.demo.orderservice.document.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

	List<Order> findByCustomerId(String customerId);

}