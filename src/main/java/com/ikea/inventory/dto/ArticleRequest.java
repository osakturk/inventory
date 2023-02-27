package com.ikea.inventory.dto;

import com.ikea.inventory.model.Article;

import java.util.List;

public class ArticleRequest {
    private List<Article> inventory;

    public List<Article> getInventory() {
        return inventory;
    }

    public void setInventory(List<Article> inventory) {
        this.inventory = inventory;
    }
}
