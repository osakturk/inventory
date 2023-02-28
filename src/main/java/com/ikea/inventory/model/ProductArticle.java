package com.ikea.inventory.model;

public class ProductArticle {
    private final String articleId;
    private final Long stock;

    public ProductArticle(String articleId, Long stock) {
        this.articleId = articleId;
        this.stock = stock;
    }

    public String getArticleId() {
        return articleId;
    }

    public Long getAmount() {
        return stock;
    }
}
