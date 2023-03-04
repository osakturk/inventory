package com.product.inventory.service;

import com.product.inventory.constant.Constants;
import com.product.inventory.dto.ArticleRequest;
import com.product.inventory.exception.EnoughMaterialNotFoundException;
import com.product.inventory.model.Article;
import com.product.inventory.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public String create(ArticleRequest articleRequest){
        articleRepository.insert(articleRequest.getInventory());
        return Constants.ARTICLE_CREATION_MESSAGE;
    }

    public List<Article> getList(){
        return articleRepository.findAll();
    }
    public void decreaseStock(String articleId, Long decreaseAmount){
        Optional<Article> article = articleRepository.findById(articleId);
        article.ifPresent(presentedArticle -> {
           if (decreaseAmount > presentedArticle.getStock()){
               throw new EnoughMaterialNotFoundException(Constants.ENOUGH_MATERIAL_NOT_FOUND_MESSAGE);
           }
           presentedArticle.setStock(presentedArticle.getStock() - decreaseAmount);
           articleRepository.save(presentedArticle);
        });
    }
}
