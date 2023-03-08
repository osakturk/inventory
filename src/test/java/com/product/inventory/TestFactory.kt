package com.product.inventory

import com.product.inventory.constant.Constants.ORDER_SUCCESSFUL_MESSAGE
import com.product.inventory.dto.*
import com.product.inventory.model.*
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

object TestFactory {


    private val DELIVERY_DATE = Instant.now().plus(1, ChronoUnit.DAYS)
    private val ORDER_DATE = Instant.now()
    private val PRODUCT_ARTICLE_LIST = listOf(ProductArticle("ArticleId","3"), ProductArticle("ArticleId2","5"))
    private val WRONG_PRODUCT_ARTICLE = listOf(ProductArticle("ArticleId", "123"), ProductArticle("ArticleId2" ,"51"))

    private val PRODUCT = Product("ProductName", PRODUCT_ARTICLE_LIST)
    private val PRODUCT_DTO = ProductDTO(arrayListOf(PRODUCT))
    private val WRONG_PRODUCT = Product("WrongProductName", WRONG_PRODUCT_ARTICLE)
    private val STATUS = Status.DELIVERED
    private val ARTICLE_ELEMENT = Article("ArticleName", "10")
    private val ARTICLE_ELEMENT2 = Article("ArticleName2", "11")
    private val ARTICLE =
        ArticleDTO(arrayListOf(ARTICLE_ELEMENT))
    private val ORDER = Order(ORDER_DATE, DELIVERY_DATE, STATUS, PRODUCT)

    private val ORDER_DETAILS = Order(
        ORDER_DATE, DELIVERY_DATE, STATUS, PRODUCT
    )

    private val ORDER_DETAILS_SELL = OrderDetails(
        ORDER_DETAILS
    )
    fun getOrderCreate(
        productId: String = "ProductId",
        message: String = ORDER_SUCCESSFUL_MESSAGE
    ) = OrderCreate(String.format(message, productId))

    fun getOrderDetails() = ORDER_DETAILS_SELL
    fun getSavedProductList() = arrayListOf(PRODUCT)
    fun getSavedProduct() = PRODUCT
    fun getProductList(): MutableMap<String, List<Product>> = mutableMapOf("products" to arrayListOf(PRODUCT))

    fun getProductRequest() = PRODUCT_DTO

    fun getArticleRequest() = ARTICLE

    fun getArticleList() = arrayListOf(ARTICLE_ELEMENT, ARTICLE_ELEMENT2)

    fun getOptionalArticle() = Optional.of(ARTICLE_ELEMENT)

    fun getOptionalProduct() = Optional.of(PRODUCT)
    fun getOptionalWrongProduct() = Optional.of(WRONG_PRODUCT)

    fun getCreatedOrder() = ORDER

    fun getOrderCreate(orderId: String) = OrderCreate(String.format(ORDER_SUCCESSFUL_MESSAGE, orderId))

    fun getOptionalOrder() = Optional.of(ORDER)


}
