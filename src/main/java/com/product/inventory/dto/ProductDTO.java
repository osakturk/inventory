package com.product.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.product.inventory.model.Product;

import java.util.List;

public class ProductDTO {
    @JsonProperty("products")
    private List<Product> productList;

    private ProductDTO(){}

    public ProductDTO(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
