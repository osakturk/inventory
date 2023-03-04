package com.product.inventory.model;

import com.product.inventory.dto.ProductRequestElement;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class Product {

    @Id
    private String productId;
    private String name;
    private String price;
    private List<ProductArticle> productArticleList;

    private Product(){}
    public Product(ProductRequestElement productRequest) {
        this.productId = UUID.randomUUID().toString();
        this.name = productRequest.getName();
        this.productArticleList = productRequest.getArticleList();
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }


    public List<ProductArticle> getProductArticleList() {
        return productArticleList;
    }
}
