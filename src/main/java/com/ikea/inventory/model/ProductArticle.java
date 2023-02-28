package com.ikea.inventory.model;

public class ProductArticle {
    private String articleId;
    private Long stock;

    public ProductArticle(String articleId, Long stock) {
        this.articleId = articleId;
        this.stock = stock;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Long getAmount() {
        return stock;
    }

    public void setAmount(Long amount) {
        this.stock = amount;
    }
}
