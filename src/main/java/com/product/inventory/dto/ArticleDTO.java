package com.product.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.product.inventory.model.Article;

import java.util.List;


public class ArticleDTO {

    @JsonProperty("inventory")
    private List<Article> inventory;


    private ArticleDTO(){}

    public ArticleDTO(List<Article> inventory) {
        this.inventory = inventory;
    }

    public List<Article> getInventory() {
        return inventory;
    }

}
