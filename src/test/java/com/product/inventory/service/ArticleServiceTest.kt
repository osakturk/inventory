package com.product.inventory.service

import com.product.inventory.TestFactory.getArticleList
import com.product.inventory.TestFactory.getArticleRequest
import com.product.inventory.TestFactory.getOptionalArticle
import com.product.inventory.constant.Constants
import com.product.inventory.exception.EnoughMaterialNotFoundException
import com.product.inventory.exception.ExistException
import com.product.inventory.repository.ArticleRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class ArticleServiceTest {
    private var articleRepository: ArticleRepository = mockk()
    private var articleService = ArticleService(articleRepository)


    @Test
    @Order(1)
    fun `create article`() {
        every {
            articleRepository.insert(getArticleRequest().inventory)
        } returns getArticleRequest().inventory
        every {
            articleRepository.existsByArticleId(any())
        } returns false

        val response = articleService.create(getArticleRequest())

        assertEquals(Constants.ARTICLE_CREATION_MESSAGE, response)
    }

    @Test
    @Order(2)
    fun `create article failed`() {
        every {
            articleRepository.insert(getArticleRequest().inventory)
        } returns getArticleRequest().inventory
        every {
            articleRepository.existsByArticleId(any())
        } returns true

        assertFailsWith<ExistException>(Constants.ARTICLE_EXIST_MESSAGE) {
            articleService.create(getArticleRequest())
        }
    }

    @Test
    @Order(3)
    fun `get article list`() {
        every {
            articleRepository.findAll()
        } returns getArticleList()

        val response = articleService.getList()

        assertEquals(getArticleList(), response)
    }

    @Test
    @Order(4)
    fun `decrease article stock count successfully`() {
        val articleId = "ArticleId"
        val decreaseAmount = 1L
        every {
            articleRepository.findById(articleId)
        } returns getOptionalArticle()
        val availableStock = getOptionalArticle().get().stock
        every {
            articleRepository.save(getOptionalArticle().get())
        } returns getOptionalArticle().get()

        articleService.decreaseStock(articleId, decreaseAmount)

        assertEquals(getOptionalArticle().get().stock.toLong(), availableStock.toLong() - decreaseAmount)
    }

    @Test
    @Order(5)
    fun `decrease article stock count failed`() {
        val articleId = "ArticleId"
        val decreaseAmount = 11L
        every {
            articleRepository.findById(articleId)
        } returns getOptionalArticle()

        assertFailsWith<EnoughMaterialNotFoundException>(Constants.ENOUGH_MATERIAL_NOT_FOUND_MESSAGE) {
            articleService.decreaseStock(articleId, decreaseAmount)
        }
    }
}

