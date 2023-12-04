package com.likhith.demo.customerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.likhith.demo.customerservice.document.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

	Customer findByName(String name);

}