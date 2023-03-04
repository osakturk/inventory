package com.product.inventory.dto;

import com.product.inventory.model.Order;
import com.product.inventory.model.Product;
import com.product.inventory.model.Status;

import java.time.Instant;

public class OrderDetails {
    private final Instant deliveryDate;
    private final Status orderStatus;
    private final Product product;

    public OrderDetails(Order order) {
        this.deliveryDate = order.getDeliveryDate();
        this.orderStatus = order.getOrderStatus();
        this.product = order.getProduct();
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
}
