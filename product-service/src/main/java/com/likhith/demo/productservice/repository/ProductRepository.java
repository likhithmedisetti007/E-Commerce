package com.likhith.demo.productservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.likhith.demo.productservice.document.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	List<Product> findBy_idIn(List<String> productIds);

}