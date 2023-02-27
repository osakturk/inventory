package com.ikea.inventory.service;

import com.ikea.inventory.dto.ArticleRequest;
import com.ikea.inventory.exception.EnoughMaterialNotFoundException;
import com.ikea.inventory.model.Article;
import com.ikea.inventory.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ikea.inventory.constant.Constants.ENOUGH_MATERIAL_NOT_FOUND_MESSAGE;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public void createInventory(ArticleRequest articleRequest){
        articleRepository.insert(articleRequest.getInventory());
    }
    public void decreaseArticleStock(String articleId, Long decreaseAmount){
        Optional<Article> Article = articleRepository.findById(articleId);
        Article.ifPresent(presentedArticle -> {
           if (decreaseAmount > presentedArticle.getAvailableStock()){
               throw new EnoughMaterialNotFoundException(ENOUGH_MATERIAL_NOT_FOUND_MESSAGE);
           }
           presentedArticle.setAvailableStock(presentedArticle.getAvailableStock() - decreaseAmount);
           articleRepository.save(presentedArticle);
        });
    }
}
