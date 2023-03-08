package com.product.inventory.controller

import com.product.inventory.TestFactory.getOrderCreate
import com.product.inventory.TestFactory.getOrderDetails
import com.product.inventory.service.OrderService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@WebMvcTest(controllers = [OrderController::class])
class OrderControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var orderService: OrderService

    @Test
    fun `sell product`() {
        val productId = "ProductId"
        Mockito.`when`(orderService.create(productId)).thenReturn(getOrderCreate())

        val result = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/api/orders").param("productId", productId)
            ).andReturn()
        Assertions.assertEquals(result.response.status, HttpStatus.CREATED.value())
    }

    @Test
    fun `get order details`() {
        val orderId = "OrderId"
        Mockito.`when`(orderService.getDetails(orderId)).thenReturn(getOrderDetails())

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/api/orders").param("orderId", orderId)).andReturn()
        Assertions.assertEquals(result.response.status, HttpStatus.OK.value())
    }
}