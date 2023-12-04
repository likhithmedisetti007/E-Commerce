package com.likhith.demo.customerservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.likhith.demo.customerservice.client.OrderClient;
import com.likhith.demo.customerservice.client.ProductClient;
import com.likhith.demo.customerservice.document.Customer;
import com.likhith.demo.customerservice.dto.Order;
import com.likhith.demo.customerservice.dto.Product;
import com.likhith.demo.customerservice.repository.CustomerRepository;

@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderClient orderClient;

	@Autowired
	ProductClient productClient;

	@Override
	public Customer getCustomerDetails(String name) {

		Customer customer = customerRepository.findByName(name);

		List<Order> orders = orderClient.getOrderDetails(customer.get_id());

		for (Order order : orders) {
			List<Product> products = productClient.getProductDetails(order.getProductIds());
			order.setProducts(products);
		}

		customer.setOrders(orders);

		return customer;
	}

}