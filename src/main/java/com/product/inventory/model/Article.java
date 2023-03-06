package com.product.inventory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Article {
    @Id
    private String id;
    @Field(name = "art_id")
    @JsonProperty("art_id")
    private String articleId;
    @JsonProperty("name")
    private String name;

    @JsonProperty("stock")
    private String stock;

    public Article() {}

    public Article(String name, String stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String availableStock) {
        this.stock = availableStock;
    }
}
