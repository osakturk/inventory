package com.ikea.inventory.service;

import com.ikea.inventory.dto.ProductRequest;
import com.ikea.inventory.exception.NotFoundException;
import com.ikea.inventory.model.Product;
import com.ikea.inventory.repository.ArticleRepository;
import com.ikea.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ikea.inventory.constant.Constants.ARTICLE_NOT_FOUND_MESSAGE;
import static com.ikea.inventory.constant.Constants.PRODUCTS;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ArticleRepository articleRepository;

    public ProductService(ProductRepository productRepository,
                          ArticleRepository articleRepository) {
        this.productRepository = productRepository;
        this.articleRepository = articleRepository;
    }

    public void createProduct(ProductRequest productRequest) {
        productRequest.getProducts().forEach( productRequestElement -> {
                    productRequestElement.getArticleList().forEach(productArticle -> {
                        if (!articleRepository.existsById(productArticle.getArticleId())){
                            throw new NotFoundException(ARTICLE_NOT_FOUND_MESSAGE);
                        }
                    });
                    productRepository.save(new Product(productRequestElement));
                });
    }

    public Map<String, List<Product>> getProductList() {
        Map<String, List<Product>> resultMap = new HashMap<>();
        resultMap.put(PRODUCTS, productRepository.findAll());
        return resultMap;
    }
}
