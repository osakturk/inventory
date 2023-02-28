package com.ikea.inventory

import com.ikea.inventory.constant.Constants.ORDER_SUCCESSFUL_MESSAGE
import com.ikea.inventory.dto.*
import com.ikea.inventory.model.*
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

object TestFactory {


    private val DELIVERY_DATE = Instant.now().plus(1, ChronoUnit.DAYS)
    private val ORDER_DATE = Instant.now()
    private val PRODUCT_ARTICLE = listOf(ProductArticle("ArticleId",3),ProductArticle("ArticleId2",5))
    private val WRONG_PRODUCT_ARTICLE = listOf(ProductArticle("ArticleId",123),ProductArticle("ArticleId2",51))
    private val PRODUCT_REQUEST_ELEMENT = ProductRequestElement("ProductName","13", PRODUCT_ARTICLE)
    private val WRONG_PRODUCT_REQUEST_ELEMENT = ProductRequestElement("ProductName","12", WRONG_PRODUCT_ARTICLE)
    private val PRODUCT = Product(PRODUCT_REQUEST_ELEMENT)
    private val WRONG_PRODUCT = Product(WRONG_PRODUCT_REQUEST_ELEMENT)
    private val STATUS = Status.DELIVERED
    private val ARTICLE = Article("ArticleId","ArticleName", 10)
    private val ARTICLE_2 = Article("ArticleId2","ArticleName2", 11)
    private val ARTICLE_LIST = arrayListOf(ARTICLE, ARTICLE_2)
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

    fun getProductRequest() = ProductRequest(arrayListOf(PRODUCT_REQUEST_ELEMENT))

    fun getArticleRequest() = ArticleRequest(ARTICLE_LIST)

    fun getArticleList() = arrayListOf(ARTICLE, ARTICLE_2)

    fun getOptionalArticle() = Optional.of(ARTICLE)

    fun getOptionalProduct() = Optional.of(PRODUCT)
    fun getOptionalWrongProduct() = Optional.of(WRONG_PRODUCT)

    fun getCreatedOrder() = ORDER

    fun getOrderCreate(orderId: String) = OrderCreate(String.format(ORDER_SUCCESSFUL_MESSAGE, orderId))

    fun getOptionalOrder() = Optional.of(ORDER)


}
