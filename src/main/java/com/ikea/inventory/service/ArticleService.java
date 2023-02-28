package com.ikea.inventory.service;

import com.ikea.inventory.dto.ArticleRequest;
import com.ikea.inventory.exception.EnoughMaterialNotFoundException;
import com.ikea.inventory.model.Article;
import com.ikea.inventory.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ikea.inventory.constant.Constants.ARTICLE_CREATION_MESSAGE;
import static com.ikea.inventory.constant.Constants.ENOUGH_MATERIAL_NOT_FOUND_MESSAGE;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public String createArticles(ArticleRequest articleRequest){
        articleRepository.insert(articleRequest.getInventory());
        return ARTICLE_CREATION_MESSAGE;
    }

    public List<Article> getArticleList(){
        return articleRepository.findAll();
    }
    public void decreaseArticleStock(String articleId, Long decreaseAmount){
        Optional<Article> article = articleRepository.findById(articleId);
        article.ifPresent(presentedArticle -> {
           if (decreaseAmount > presentedArticle.getStock()){
               throw new EnoughMaterialNotFoundException(ENOUGH_MATERIAL_NOT_FOUND_MESSAGE);
           }
           presentedArticle.setStock(presentedArticle.getStock() - decreaseAmount);
           articleRepository.save(presentedArticle);
        });
    }
}
