package com.ikea.inventory.service

import com.ikea.inventory.TestFactory.getCreatedOrder
import com.ikea.inventory.TestFactory.getOptionalArticle
import com.ikea.inventory.TestFactory.getOptionalProduct
import com.ikea.inventory.TestFactory.getOrderCreate
import com.ikea.inventory.repository.ArticleRepository
import com.ikea.inventory.repository.OrderRepository
import com.ikea.inventory.repository.ProductRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OrderServiceTest {

    private var orderRepository: OrderRepository = mockk()
    private var productRepository: ProductRepository = mockk()
    private var articleRepository: ArticleRepository = mockk()
    private var articleService = ArticleService(articleRepository)
    private var orderService = OrderService(orderRepository, productRepository, articleService)


    @Test
    fun `sell successfully`() {
        val productId = "ProductId"
        every { productRepository.findById(productId) } returns getOptionalProduct()
        every { articleRepository.findById(any()) } returns getOptionalArticle()
        every { articleRepository.save(getOptionalArticle().get()) } returns getOptionalArticle().get()
        every { orderRepository.save(any()) } returns getCreatedOrder()

        val response = orderService.sell(productId)

        assertEquals(getOrderCreate(getCreatedOrder().orderId).message, response.message)
    }

    @Test
    fun `sell failed due to article amount`() {
        val productId = "ProductId"
        every { productRepository.findById(productId) } returns getOptionalProduct()
        every { articleRepository.findById(any()) } returns getOptionalArticle()
        every { articleRepository.save(getOptionalArticle().get()) } returns getOptionalArticle().get()
        every { orderRepository.save(any()) } returns getCreatedOrder()

        val response = orderService.sell(productId)

        assertEquals(getOrderCreate(getCreatedOrder().orderId).message, response.message)
    }
}