package com.product.inventory.service

import com.product.inventory.TestFactory.getCreatedOrder
import com.product.inventory.TestFactory.getOptionalArticle
import com.product.inventory.TestFactory.getOptionalOrder
import com.product.inventory.TestFactory.getOptionalProduct
import com.product.inventory.TestFactory.getOptionalWrongProduct
import com.product.inventory.TestFactory.getOrderCreate
import com.product.inventory.constant.Constants
import com.product.inventory.exception.EnoughMaterialNotFoundException
import com.product.inventory.exception.NotFoundException
import com.product.inventory.repository.ArticleRepository
import com.product.inventory.repository.OrderRepository
import com.product.inventory.repository.ProductRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertFailsWith

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

        val response = orderService.create(productId)

        assertEquals(getOrderCreate(getCreatedOrder().orderId).message(), response.message())
    }

    @Test
    fun `sell failed due to product`() {
        val productId = "ProductId"
        every { productRepository.findById(productId) } returns Optional.empty()

        assertFailsWith<NotFoundException>(Constants.PRODUCT_NOT_FOUND_MESSAGE) {
            orderService.create(productId)
        }
    }

    @Test
    fun `sell failed due to article amount`() {
        val productId = "ProductId"
        every { productRepository.findById(productId) } returns getOptionalWrongProduct()
        every { articleRepository.findById(any()) } returns getOptionalArticle()
        every { articleRepository.save(getOptionalArticle().get()) } returns getOptionalArticle().get()

        assertFailsWith<EnoughMaterialNotFoundException>(Constants.ENOUGH_MATERIAL_NOT_FOUND_MESSAGE) {
            orderService.create(productId)
        }
    }

    @Test
    fun `get order details successfully`() {
        val orderId = "OrderId"
        every {
            orderRepository.findById(any())
        } returns getOptionalOrder()

        val response = orderService.getDetails(orderId)

        assertEquals(getOptionalOrder().get().orderStatus, response.orderStatus)
        assertEquals(getOptionalOrder().get().deliveryDate, response.deliveryDate)
        assertEquals(getOptionalOrder().get().product, response.product)
    }

    @Test
    fun `get order details failed`() {
        val orderId = "OrderId"
        every {
            orderRepository.findById(any())
        } returns Optional.empty()

        assertFailsWith<NotFoundException>(Constants.ORDER_NOT_FOUND_MESSAGE) {
            orderService.getDetails(orderId)
        }
    }
}