package com.product.inventory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProductArticle {

    @JsonProperty("art_id")
    private String articleId;

    @JsonProperty("amount_of")
    private String stock;

    public ProductArticle() {}

    public ProductArticle(String articleId, String stock) {
        this.articleId = articleId;
        this.stock = stock;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String availableStock) {
        this.stock = availableStock;
    }
}
