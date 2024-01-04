package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.GetAllProductsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetAllProductsUseCaseImplTest {

    @MockK
    lateinit var repository: ProductRepository

    private lateinit var getAllProductsUseCase: GetAllProductsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getAllProductsUseCase = GetAllProductsUseCaseImpl(
            repository = repository
        )
    }

    @Test
    fun success() {
        /* Arrange */
        val expectedResult: List<ProductInfo> =
            listOf(
                ProductInfo(
                    id = 0,
                    title = "test",
                    imageUrl = "",
                    price = "",
                    count = 0
                )
            )

        coEvery { repository.getAllProducts() } returns expectedResult

        /* Act */
        runTest {
            val result = getAllProductsUseCase()
            /* Assert */
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun error() {
        /* Arrange */
        val expectedResult: List<ProductInfo> = listOf()

        coEvery { repository.getAllProducts() } returns expectedResult

        /* Act */
        runTest {
            val result = getAllProductsUseCase()
            /* Assert */
            assertEquals(expectedResult, result)
        }
    }
}
