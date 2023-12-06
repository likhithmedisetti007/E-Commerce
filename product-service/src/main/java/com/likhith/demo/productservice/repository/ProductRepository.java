package com.likhith.demo.productservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.likhith.demo.productservice.document.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	List<Product> findByIdIn(List<String> productIds);

	Optional<Product> findByNameAndCostAndCategory(String name, String cost, String category);

}