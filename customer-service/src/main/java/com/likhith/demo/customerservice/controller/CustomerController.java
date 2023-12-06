package com.likhith.demo.customerservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.demo.customerservice.document.Customer;
import com.likhith.demo.customerservice.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/getCustomerDetails/{id}")
	public ResponseEntity<?> getOrderDetails(@PathVariable("id") String id) {
		Optional<Customer> customerOptional = customerService.getCustomerDetails(id);

		if (customerOptional.isPresent()) {
			return ResponseEntity.ok().body(customerOptional.get());
		}
		return ResponseEntity.notFound().build();

	}

	@PostMapping("/addCustomerDetails")
	public ResponseEntity<?> addCustomerDetails(@RequestBody List<Customer> customers) {
		List<String> customerIds = customerService.addCustomerDetails(customers);
		if (!CollectionUtils.isEmpty(customerIds)) {
			return ResponseEntity.ok().body(customerIds);
		}
		return ResponseEntity.internalServerError().body("Order Details Not Added");
	}

	@PutMapping("/updateCustomerDetails/{id}")
	public ResponseEntity<?> updateCustomerDetails(@RequestBody Customer customer, @PathVariable("id") String id) {
		Boolean updateSuccess = customerService.updateCustomerDetails(customer, id);
		if (updateSuccess) {
			return ResponseEntity.ok().body("Customer Details Updated");
		}
		return ResponseEntity.internalServerError().body("Customer Details Not Updated");
	}

	@DeleteMapping("/deleteCustomerDetails/{id}")
	public ResponseEntity<?> deleteCustomerDetails(@PathVariable("id") String id) {
		Boolean deleteSuccess = customerService.deleteCustomerDetails(id);
		if (deleteSuccess) {
			return ResponseEntity.ok().body("Customer Details Deleted");
		}
		return ResponseEntity.internalServerError().body("Customer Details Not Deleted");
	}

}