package com.ikea.inventory.repository;

import com.ikea.inventory.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
