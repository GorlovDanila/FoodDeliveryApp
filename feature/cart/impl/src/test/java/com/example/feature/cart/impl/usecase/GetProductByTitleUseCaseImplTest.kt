package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.GetProductByTitleUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetProductByTitleUseCaseImplTest {

    @MockK
    lateinit var repository: ProductRepository

    private lateinit var getProductByTitleUseCase: GetProductByTitleUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getProductByTitleUseCase = GetProductByTitleUseCaseImpl(
            repository = repository
        )
    }

    @Test
    fun success() {
        /* Arrange */
        val expectedResult =
            ProductInfo(
                id = 0,
                title = "test",
                imageUrl = "",
                price = "",
                count = 0
            )
        val title = "test"

        coEvery { repository.getProductByTitle(title) } returns expectedResult

        /* Act */
        runTest {
            val result = getProductByTitleUseCase(title)
            /* Assert */
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun fail() {
        /* Arrange */
        val expectedResult: ProductInfo? = null
        val title = "test"

        coEvery { repository.getProductByTitle(title) } returns expectedResult

        /* Act */
        runTest {
            val result = getProductByTitleUseCase(title)
            /* Assert */
            assertEquals(expectedResult, result)
        }
    }
}
