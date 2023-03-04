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

    //We can create articles using this method
    public String create(ArticleRequest articleRequest){
        articleRepository.insert(articleRequest.getInventory());
        return Constants.ARTICLE_CREATION_MESSAGE;
    }

    //We can get article list using this method
    public List<Article> getList(){
        return articleRepository.findAll();
    }

    //We reduce stocks using this method. This method is triggered by the order creation API
    public void decreaseStock(String articleId, Long decreaseAmount){
        Optional<Article> article = articleRepository.findById(articleId);
        article.ifPresent(presentedArticle -> {
            //If the article doesn't have enough material, it should throw a meaningful exception
           if (decreaseAmount > presentedArticle.getStock()){
               throw new EnoughMaterialNotFoundException(Constants.ENOUGH_MATERIAL_NOT_FOUND_MESSAGE);
           }
           presentedArticle.setStock(presentedArticle.getStock() - decreaseAmount);
           articleRepository.save(presentedArticle);
        });
    }
}
