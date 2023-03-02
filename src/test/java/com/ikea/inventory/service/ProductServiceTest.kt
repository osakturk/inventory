package com.ikea.inventory.service

import com.ikea.inventory.TestFactory.getProductList
import com.ikea.inventory.TestFactory.getProductRequest
import com.ikea.inventory.TestFactory.getSavedProduct
import com.ikea.inventory.TestFactory.getSavedProductList
import com.ikea.inventory.constant.Constants
import com.ikea.inventory.exception.NotFoundException
import com.ikea.inventory.repository.ArticleRepository
import com.ikea.inventory.repository.ProductRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ProductServiceTest {

    private var productRepository: ProductRepository = mockk()
    private var articleRepository: ArticleRepository = mockk()
    private var productService = ProductService(productRepository,articleRepository)

    @Test
    fun `create products successfully`() {
        every { articleRepository.existsById(any()) } returns true
        every {
            productRepository.save(any())
        } returns getSavedProduct()

        val response = productService.create(getProductRequest())

        Assertions.assertEquals(Constants.PRODUCTS_CREATION_MESSAGE, response)
    }

    @Test
    fun `create products failed due to article existence`() {
        every { articleRepository.existsById(any()) } returns false

        assertFailsWith<NotFoundException>(Constants.ARTICLE_NOT_FOUND_MESSAGE) {
            productService.create(getProductRequest())
        }
    }

    @Test
    fun `get product list`() {
        every {
            productRepository.findAll()
        } returns getSavedProductList()

        val response = productService.list

        assertEquals(getProductList(), response)
    }
}