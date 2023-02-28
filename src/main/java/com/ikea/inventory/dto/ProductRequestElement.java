package com.ikea.inventory.dto;

import com.ikea.inventory.model.ProductArticle;

import java.util.List;

public class ProductRequestElement {
    private String name;
    private List<ProductArticle> articleList;

    private ProductRequestElement(){}

    public ProductRequestElement(String name, List<ProductArticle> articleList) {
        this.name = name;
        this.articleList = articleList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductArticle> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ProductArticle> articleList) {
        this.articleList = articleList;
    }
}
