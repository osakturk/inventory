package com.product.inventory.service;

import com.product.inventory.constant.Constants;
import com.product.inventory.exception.EnoughMaterialNotFoundException;
import com.product.inventory.dto.ArticleDTO;
import com.product.inventory.exception.ExistException;
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
    public String create(ArticleDTO articleDto) {
        articleDto.getInventory().forEach(article -> {
            if (articleRepository.existsByArticleId(article.getArticleId())) {
                throw new ExistException(Constants.ARTICLE_EXIST_MESSAGE);
            }
        });
        articleRepository.insert(articleDto.getInventory());
        return Constants.ARTICLE_CREATION_MESSAGE;
    }

    //We can get article list using this method
    public List<Article> getList() {
        return articleRepository.findAll();
    }

    //We reduce stocks using this method. This method is triggered by the order creation API
    public void decreaseStock(String articleId, Long decreaseAmount) {
        Optional<Article> article = articleRepository.findById(articleId);
        article.ifPresent(presentedArticle -> {
            if (decreaseAmount > Long.parseLong(presentedArticle.getStock())) {
                throw new EnoughMaterialNotFoundException(Constants.ENOUGH_MATERIAL_NOT_FOUND_MESSAGE);
            }
            presentedArticle.setStock(String.valueOf(Long.parseLong(presentedArticle.getStock()) - decreaseAmount));
            articleRepository.save(presentedArticle);
        });
    }
}
