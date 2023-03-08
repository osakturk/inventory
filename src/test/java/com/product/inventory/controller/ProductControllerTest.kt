package com.product.inventory.controller

import com.google.gson.Gson
import com.product.inventory.TestFactory.getProductList
import com.product.inventory.TestFactory.getProductRequest
import com.product.inventory.constant.Constants
import com.product.inventory.service.ProductService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@WebMvcTest(controllers = [ProductController::class])
class ProductControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var productService: ProductService

    @Test
    fun `create product`() {
        Mockito.`when`(productService.create(getProductRequest())).thenReturn(Constants.PRODUCTS_CREATION_MESSAGE)

        val result = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/api/products")
                    .content(
                        Gson().toJson(
                            getProductRequest()
                        )
                    )
                    .contentType("application/json")
            ).andReturn()
        assertEquals(result.response.status, HttpStatus.CREATED.value())
    }

    @Test
    fun `get product list`() {
        Mockito.`when`(productService.getList()).thenReturn(getProductList())

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/api/products")).andReturn()
        assertEquals(result.response.status, HttpStatus.OK.value())
    }
}