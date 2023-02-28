package com.ikea.inventory.dto;

import com.ikea.inventory.model.ProductArticle;

import java.util.List;

public class ProductRequestElement {
    private String name;
    private String price;
    private List<ProductArticle> articleList;

    private ProductRequestElement(){}

    public ProductRequestElement(String name, String price, List<ProductArticle> articleList) {
        this.name = name;
        this.price = price;
        this.articleList = articleList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<ProductArticle> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ProductArticle> articleList) {
        this.articleList = articleList;
    }
}
