package com.product.inventory.controller

import com.google.gson.Gson
import com.product.inventory.constant.Constants
import com.product.inventory.dto.ArticleRequest
import com.product.inventory.service.ArticleService
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@WebMvcTest(controllers = [ArticleController::class])
class ArticleControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private var articleRequest: ArticleRequest = mockk()

    @MockBean
    lateinit var articleService: ArticleService

    @Test
    fun `create article`() {
        Mockito.`when`(articleService.create(articleRequest)).thenReturn(Constants.ARTICLE_CREATION_MESSAGE)

        val result = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/api/articles")
                    .content(
                        Gson().toJson(
                            articleRequest
                        )
                    )
                    .contentType("application/json")
            ).andReturn()
        assertEquals(result.response.status, HttpStatus.CREATED.value())
    }

    @Test
    fun `get article list`() {
        Mockito.`when`(articleService.list).thenReturn(arrayListOf())

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/api/articles")).andReturn()
        assertEquals(result.response.status, HttpStatus.OK.value())
    }
}