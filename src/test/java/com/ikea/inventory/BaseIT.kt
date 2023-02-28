package com.ikea.inventory

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext
open class BaseIT {
    companion object {
        @Container
        val mongoDBContainer = MongoDBContainer("mongo")

        @JvmStatic
        @DynamicPropertySource
        fun dynamicPropertiesRegister(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri", mongoDBContainer::getConnectionString)
            registry.add("spring.data.mongodb.database") { "edine" }
        }
    }
}
