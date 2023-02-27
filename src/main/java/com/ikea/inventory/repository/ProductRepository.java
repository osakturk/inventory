package com.ikea.inventory.repository;

import com.ikea.inventory.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findByProductId(String productId);
}
