package com.product.inventory.repository;

import com.product.inventory.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, String> {
}
