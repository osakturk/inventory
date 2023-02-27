package com.ikea.inventory.controller;

import com.ikea.inventory.dto.OrderCreate;
import com.ikea.inventory.dto.OrderDetails;
import com.ikea.inventory.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderCreate> sell(@RequestParam("productId") String productId){
        return ResponseEntity.created(URI.create("/")).body(orderService.sell(productId));
    }

    @GetMapping
    public ResponseEntity<OrderDetails> orderTrack(@RequestParam("orderId") String orderId){
        return ResponseEntity.ok().body(orderService.getOrderDetails(orderId));
    }
}
