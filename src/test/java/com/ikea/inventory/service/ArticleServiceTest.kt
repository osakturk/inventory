package com.ikea.inventory.service

import com.ikea.inventory.TestFactory.getArticleList
import com.ikea.inventory.TestFactory.getArticleRequest
import com.ikea.inventory.TestFactory.getOptionalArticle
import com.ikea.inventory.constant.Constants
import com.ikea.inventory.exception.EnoughMaterialNotFoundException
import com.ikea.inventory.repository.ArticleRepository
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

        val response = articleService.createArticles(getArticleRequest())

        assertEquals(Constants.ARTICLE_CREATION_MESSAGE, response)
    }

    @Test
    @Order(2)
    fun `get article list`() {
        every {
            articleRepository.findAll()
        } returns getArticleList()

        val response = articleService.getArticleList()

        assertEquals(getArticleList(), response)
    }

    @Test
    @Order(3)
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

        articleService.decreaseArticleStock(articleId, decreaseAmount)

        assertEquals(getOptionalArticle().get().stock, availableStock - decreaseAmount)
    }

    @Test
    @Order(4)
    fun `decrease article stock count failed`() {
        val articleId = "ArticleId"
        val decreaseAmount = 11L
        every {
            articleRepository.findById(articleId)
        } returns getOptionalArticle()

        assertFailsWith<EnoughMaterialNotFoundException>(Constants.ENOUGH_MATERIAL_NOT_FOUND_MESSAGE) {
            articleService.decreaseArticleStock(articleId, decreaseAmount)
        }
    }
}
