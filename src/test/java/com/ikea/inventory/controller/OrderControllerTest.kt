package com.ikea.inventory.controller

import com.ikea.inventory.TestFactory.getOrderCreate
import com.ikea.inventory.TestFactory.getOrderDetails
import com.ikea.inventory.service.OrderService
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
        Mockito.`when`(orderService.sell(productId)).thenReturn(getOrderCreate())

        val result = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/api/order").param("productId", productId)
            ).andReturn()
        Assertions.assertEquals(result.response.status, HttpStatus.CREATED.value())
    }

    @Test
    fun `get order details`() {
        val orderId = "OrderId"
        Mockito.`when`(orderService.getOrderDetails(orderId)).thenReturn(getOrderDetails())

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/api/order").param("orderId", orderId)).andReturn()
        Assertions.assertEquals(result.response.status, HttpStatus.OK.value())
    }
}