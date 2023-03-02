package com.ikea.inventory.controller;

import com.ikea.inventory.dto.ProductRequest;
import com.ikea.inventory.model.Product;
import com.ikea.inventory.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody ProductRequest productRequest){
        return ResponseEntity.created(URI.create("/")).body(productService.create(productRequest));
    }

    @GetMapping
    public ResponseEntity<Map<String, List<Product>>> list(){
        return ResponseEntity.ok().body(productService.getList());
    }
}
