package com.likhith.demo.customerservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.likhith.demo.customerservice.document.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

	Optional<Customer> findById(String id);

	List<Customer> findByIdIn(List<String> ids);

}