package com.product.inventory.dto;


import java.util.List;

public class ProductRequest {
    private List<ProductRequestElement> products;

    private ProductRequest(){}

    public ProductRequest(List<ProductRequestElement> products) {
        this.products = products;
    }

    public List<ProductRequestElement> getProducts() {
        return products;
    }

}