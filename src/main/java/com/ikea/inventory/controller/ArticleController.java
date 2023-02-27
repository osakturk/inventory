package com.ikea.inventory.controller;

import com.ikea.inventory.dto.ArticleRequest;
import com.ikea.inventory.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.ikea.inventory.constant.Constants.ARTICLE_CREATION_MESSAGE;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<String> createArticles(@RequestBody ArticleRequest request){
        articleService.createInventory(request);
        return ResponseEntity.created(URI.create("/")).body(ARTICLE_CREATION_MESSAGE);
    }
}
