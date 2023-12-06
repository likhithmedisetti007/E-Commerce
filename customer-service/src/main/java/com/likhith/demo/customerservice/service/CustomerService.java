package com.likhith.demo.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.likhith.demo.customerservice.document.Customer;

@Service
public interface CustomerService {

	public Optional<Customer> getCustomerDetails(String id);

	public List<String> addCustomerDetails(List<Customer> customers);

	public Boolean updateCustomerDetails(Customer customer, String id);

	public Boolean deleteCustomerDetails(String id);

}