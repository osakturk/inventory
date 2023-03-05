package com.product.inventory.controller;

import com.product.inventory.dto.OrderCreate;
import com.product.inventory.dto.OrderDetails;
import com.product.inventory.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderCreate> create(@RequestParam("productId") String productId){
        return ResponseEntity.created(URI.create("/")).body(orderService.create(productId));
    }

    @GetMapping
    public ResponseEntity<OrderDetails> track(@RequestParam("orderId") String orderId){
        return ResponseEntity.ok().body(orderService.getDetails(orderId));
    }
}
