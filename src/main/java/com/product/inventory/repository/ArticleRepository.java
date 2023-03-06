package com.product.inventory.repository;

import com.product.inventory.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArticleRepository extends MongoRepository<Article, String> {

    Optional<Article> findByArticleId(String s);


    boolean existsByArticleId(String articleId);
}
