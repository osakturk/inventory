package com.ikea.inventory.repository;

import com.ikea.inventory.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {

}
