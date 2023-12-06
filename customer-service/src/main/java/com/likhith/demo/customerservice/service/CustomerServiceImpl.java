package com.likhith.demo.customerservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.likhith.demo.customerservice.client.OrderClient;
import com.likhith.demo.customerservice.document.Customer;
import com.likhith.demo.customerservice.dto.Order;
import com.likhith.demo.customerservice.repository.CustomerRepository;

@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderClient orderClient;

	@Override
	public Optional<Customer> getCustomerDetails(String id) {

		Optional<Customer> customerOptional = customerRepository.findById(id);

		if (customerOptional.isPresent()) {
			List<Order> orders = orderClient.getOrderDetails(customerOptional.get().getOrdersIds());
			customerOptional.get().setOrders(orders);
		}
		return customerOptional;
	}

	@Override
	public List<String> addCustomerDetails(List<Customer> customers) {

		List<String> customerIds = new ArrayList<>();

		for (Customer customer : customers) {
			Customer customerFromDB = customerRepository.insert(customer);

			List<Order> orders = customer.getOrders();
			orders.forEach(s -> s.setCustomerId(customer.getId()));
			List<String> orderIds = orderClient.addOrderDetails(orders);

			if (!CollectionUtils.isEmpty(orderIds)) {
				customerFromDB.setOrdersIds(orderIds);
				customerRepository.save(customerFromDB);
			}

			customerIds.add(customerFromDB.getId());
		}
		return customerIds;
	}

	@Override
	public Boolean updateCustomerDetails(Customer customer, String id) {

		Boolean updateSuccess;

		try {
			Optional<Customer> customerOptional = customerRepository.findById(id);

			if (customerOptional.isPresent()) {
				Customer customerFromDB = customerOptional.get();

				// Implement the below logic using any mapper
				if (null != customer.getName()) {
					customerFromDB.setName(customer.getName());
				}
				if (null != customer.getEmail()) {
					customerFromDB.setEmail(customer.getEmail());
				}
				if (null != customer.getPhone()) {
					customerFromDB.setPhone(customer.getPhone());
				}
				if (null != customer.getCity()) {
					customerFromDB.setCity(customer.getCity());
				}
				if (null != customer.getState()) {
					customerFromDB.setState(customer.getState());
				}
				if (null != customer.getCountry()) {
					customerFromDB.setCountry(customer.getCountry());
				}

				List<Order> newOrders = new ArrayList<>();
				List<String> newOrderIds = new ArrayList<>();

				if (!CollectionUtils.isEmpty(customer.getOrders())) {
					for (Order order : customer.getOrders()) {
						Boolean isOrderExists = orderClient.checkOrderExists(order.getId());
						if (isOrderExists) {
							orderClient.updateOrderDetails(order, order.getId());
						} else {
							order.setCustomerId(customer.getId());
							newOrders.add(order);
						}
					}

					if (!CollectionUtils.isEmpty(newOrders)) {
						newOrderIds = orderClient.addOrderDetails(newOrders);
					}

					if (CollectionUtils.isEmpty(customerFromDB.getOrdersIds())) {
						customerFromDB.setOrdersIds(newOrderIds);
					} else {
						customerFromDB.getOrdersIds().addAll(newOrderIds);
					}
				}

				customerRepository.save(customerFromDB);

				updateSuccess = true;
			} else {
				updateSuccess = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			updateSuccess = false;
		}

		return updateSuccess;
	}

	@Override
	public Boolean deleteCustomerDetails(String id) {

		Boolean deleteSuccess;

		try {
			Optional<Customer> customerOptional = customerRepository.findById(id);

			if (customerOptional.isPresent()) {
				Customer customerFromDB = customerOptional.get();

				customerRepository.delete(customerFromDB);

				if (!CollectionUtils.isEmpty(customerFromDB.getOrdersIds())) {
					for (String orderId : customerFromDB.getOrdersIds()) {
						orderClient.deleteOrderDetails(orderId);
					}
				}

				deleteSuccess = true;
			} else {
				deleteSuccess = false;
			}
		} catch (Exception e) {
			deleteSuccess = false;
		}
		return deleteSuccess;
	}
}