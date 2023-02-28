package com.ikea.inventory.model;

import org.springframework.data.annotation.Id;

public class Article {
    @Id
    private String articleId;
    private String name;
    private Long stock;

    private Article(){}

    public Article(String articleId, String name, Long stock) {
        this.articleId = articleId;
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

    public Long getStock() {
        return stock;
    }

    public void setStock(Long availableStock) {
        this.stock = availableStock;
    }
}
