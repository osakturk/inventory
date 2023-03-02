package com.ikea.inventory.service;

import com.ikea.inventory.dto.OrderCreate;
import com.ikea.inventory.dto.OrderDetails;
import com.ikea.inventory.exception.NotFoundException;
import com.ikea.inventory.model.Order;
import com.ikea.inventory.model.Product;
import com.ikea.inventory.model.Status;
import com.ikea.inventory.repository.OrderRepository;
import com.ikea.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static com.ikea.inventory.constant.Constants.ORDER_NOT_FOUND_MESSAGE;
import static com.ikea.inventory.constant.Constants.ORDER_SUCCESSFUL_MESSAGE;
import static com.ikea.inventory.constant.Constants.PRODUCT_NOT_FOUND_MESSAGE;

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

    public OrderCreate create(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()){
            throw new NotFoundException(PRODUCT_NOT_FOUND_MESSAGE);
        }
        product.get().getProductArticleList().forEach(productArticle -> articleService.decreaseStock(productArticle.getArticleId(), productArticle.getAmount()));
        Order savedOrder = orderRepository.save(new Order(Instant.now(), Instant.now().plus(1, ChronoUnit.DAYS), Status.PENDING, product.get()));
        return new OrderCreate(String.format(ORDER_SUCCESSFUL_MESSAGE, savedOrder.getOrderId()));
    }

    public OrderDetails getDetails(String orderId){
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()){
            throw new NotFoundException(ORDER_NOT_FOUND_MESSAGE);
        }
        return new OrderDetails(order.get());
    }
}
