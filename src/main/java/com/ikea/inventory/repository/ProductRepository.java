package com.ikea.inventory.repository;

import com.ikea.inventory.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Long, Product> {
}
