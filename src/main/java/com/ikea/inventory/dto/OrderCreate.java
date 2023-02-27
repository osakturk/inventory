package com.ikea.inventory.dto;

public class OrderCreate {
    private String message;

    public OrderCreate(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
