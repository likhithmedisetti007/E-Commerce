package com.likhith.demo.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.demo.customerservice.document.Customer;
import com.likhith.demo.customerservice.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/getCustomerDetails/{name}")
	public ResponseEntity<?> getOrderDetails(@PathVariable("name") String name) {
		Customer customer = customerService.getCustomerDetails(name);

		if (ObjectUtils.isEmpty(customer)) {
			return ResponseEntity.badRequest().body("Invalid Request / No Data Found");
		} else {
			return ResponseEntity.ok(customer);
		}
	}

}