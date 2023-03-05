package com.product.inventory.service;

import com.product.inventory.constant.Constants;
import com.product.inventory.dto.OrderCreate;
import com.product.inventory.dto.OrderDetails;
import com.product.inventory.model.Order;
import com.product.inventory.model.Product;
import com.product.inventory.repository.OrderRepository;
import com.product.inventory.repository.ProductRepository;
import com.product.inventory.exception.NotFoundException;
import com.product.inventory.model.Status;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private final ArticleService articleService;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository, ArticleService articleService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.articleService = articleService;
    }

    //We can create an order using this method. Additionally, we trigger the article service from this method.
    public OrderCreate create(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()){
            throw new NotFoundException(Constants.PRODUCT_NOT_FOUND_MESSAGE);
        }
        product.get().getProductArticleList().forEach(productArticle -> articleService.decreaseStock(productArticle.getArticleId(), productArticle.getAmount()));
        Order savedOrder = orderRepository.save(new Order(Instant.now(), Instant.now().plus(1, ChronoUnit.DAYS), Status.PENDING, product.get()));
        return new OrderCreate(String.format(Constants.ORDER_SUCCESSFUL_MESSAGE, savedOrder.getOrderId()));
    }

    //This method returns order details
    public OrderDetails getDetails(String orderId){
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()){
            throw new NotFoundException(Constants.ORDER_NOT_FOUND_MESSAGE);
        }
        return new OrderDetails(order.get());
    }
}
