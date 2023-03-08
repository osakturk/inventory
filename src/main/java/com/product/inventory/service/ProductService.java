package com.product.inventory.service;

import com.product.inventory.constant.Constants;
import com.product.inventory.dto.ProductDTO;
import com.product.inventory.exception.NotFoundException;
import com.product.inventory.model.Product;
import com.product.inventory.repository.ProductRepository;
import com.product.inventory.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ArticleRepository articleRepository;

    public ProductService(ProductRepository productRepository,
                          ArticleRepository articleRepository) {
        this.productRepository = productRepository;
        this.articleRepository = articleRepository;
    }

    //We can create a product using this method
    public String create(ProductDTO productDTO) {
        productDTO.getProductList().forEach(productElement -> {
            productElement.getProductArticleList().forEach(productArticle -> {
                //We check the article repository for available material.
                // If there are no materials, we throw an exception
                if (!articleRepository.existsByArticleId(productArticle.getArticleId())) {
                    throw new NotFoundException(Constants.ARTICLE_NOT_FOUND_MESSAGE);
                }
            });
            productRepository.save(new Product(productElement.getName(), productElement.getProductArticleList()));
        });
        return Constants.PRODUCTS_CREATION_MESSAGE;
    }


    //This method returns a list of products
    public Map<String, List<Product>> getList() {
        Map<String, List<Product>> resultMap = new HashMap<>();
        resultMap.put(Constants.PRODUCTS, productRepository.findAll());
        return resultMap;
    }
}
