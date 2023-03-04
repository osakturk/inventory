package com.product.inventory.service;

import com.product.inventory.constant.Constants;
import com.product.inventory.dto.ProductRequest;
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

    public String create(ProductRequest productRequest) {
        productRequest.getProducts().forEach( productRequestElement -> {
                    productRequestElement.getArticleList().forEach(productArticle -> {
                        if (!articleRepository.existsById(productArticle.getArticleId())){
                            throw new NotFoundException(Constants.ARTICLE_NOT_FOUND_MESSAGE);
                        }
                    });
                    productRepository.save(new Product(productRequestElement));
                });
        return Constants.PRODUCTS_CREATION_MESSAGE;
    }

    public Map<String, List<Product>> getList() {
        Map<String, List<Product>> resultMap = new HashMap<>();
        resultMap.put(Constants.PRODUCTS, productRepository.findAll());
        return resultMap;
    }
}
