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

import static com.ikea.inventory.constant.Constants.PRODUCTS_CREATION_MESSAGE;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
        return ResponseEntity.created(URI.create("/")).body(PRODUCTS_CREATION_MESSAGE);
    }

    @GetMapping
    public ResponseEntity<Map<String, List<Product>>> productList(){
        return ResponseEntity.ok().body(productService.getProductList());
    }
}
