package com.ikea.inventory.model;

import org.springframework.data.annotation.Id;

public class Article {
    @Id
    private String articleId;
    private String name;
    private Long availableStock;

    public Article(String articleId, String name, Long stock) {
        this.articleId = articleId;
        this.name = name;
        this.availableStock = stock;
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

    public Long getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Long availableStock) {
        this.availableStock = availableStock;
    }
}
