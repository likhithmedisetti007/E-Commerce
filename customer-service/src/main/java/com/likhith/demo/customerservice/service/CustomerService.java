package com.likhith.demo.customerservice.service;

import org.springframework.stereotype.Service;

import com.likhith.demo.customerservice.document.Customer;

@Service
public interface CustomerService {

	public Customer getCustomerDetails(String name);

}