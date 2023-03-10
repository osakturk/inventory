package com.product.inventory.controller;

import com.product.inventory.dto.ArticleDTO;
import com.product.inventory.model.Article;
import com.product.inventory.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ArticleDTO article){
        return ResponseEntity.created(URI.create("/")).body(articleService.create(article));
    }

    @GetMapping
    public ResponseEntity<List<Article>> list(){
        return ResponseEntity.ok().body(articleService.getList());
    }
}
