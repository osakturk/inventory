package com.ikea.inventory.model;

import com.ikea.inventory.dto.ProductRequestElement;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class Product {

    @Id
    private String productId;
    private String name;
    private String price;
    private List<ProductArticle> productArticleList;

    private Product(){}
    public Product(ProductRequestElement productRequest) {
        this.productId = UUID.randomUUID().toString();
        this.name = productRequest.getName();
        this.productArticleList = productRequest.getArticleList();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductArticle> getProductArticleList() {
        return productArticleList;
    }

    public void setProductArticleList(List<ProductArticle> productArticleList) {
        this.productArticleList = productArticleList;
    }
}
