package com.ikea.inventory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document
public class Order {

    @Id
    private String orderId;
    private Instant orderDate;
    private Instant deliveryDate;
    private Status orderStatus;
    private Product product;


    public Order() {
    }

    public Order(Instant orderDate, Instant deliveryDate, Status orderStatus, Product product) {
        this.orderId = UUID.randomUUID().toString();
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.product = product;
    }

    public String getOrderId() {
        return orderId;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public Instant getDeliveryDate() {
        return deliveryDate;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
