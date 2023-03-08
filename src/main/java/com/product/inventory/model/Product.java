package com.product.inventory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Document
public class Product {

    @Id
    private String productId;

    @JsonProperty("name")
    private String name;

    @Field(name = "contain_articles")
    @JsonProperty("contain_articles")
    private List<ProductArticle> productArticleList;

    public Product() {
    }

    public Product(String name, List<ProductArticle> productArticleList) {
        this.productId = UUID.randomUUID().toString();
        this.name = name;
        this.productArticleList = productArticleList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<ProductArticle> getProductArticleList() {
        return productArticleList;
    }

    public void setProductArticleList(List<ProductArticle> productArticleList) {
        this.productArticleList = productArticleList;
    }
}
