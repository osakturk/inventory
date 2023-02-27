package com.ikea.inventory.dto;

import com.ikea.inventory.model.Order;
import com.ikea.inventory.model.Product;
import com.ikea.inventory.model.Status;

import java.time.Instant;

public class OrderDetails {
    private Instant deliveryDate;
    private Status orderStatus;
    private Product product;

    public OrderDetails(Order order) {
        this.deliveryDate = order.getDeliveryDate();
        this.orderStatus = order.getOrderStatus();
        this.product = order.getProduct();
    }

    public Instant getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Instant deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}