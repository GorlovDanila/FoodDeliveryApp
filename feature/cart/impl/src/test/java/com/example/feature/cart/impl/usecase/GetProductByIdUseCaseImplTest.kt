package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.GetProductByIdUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetProductByIdUseCaseImplTest {

    @MockK
    lateinit var repository: ProductRepository

    private lateinit var getProductByIdUseCase: GetProductByIdUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getProductByIdUseCase = GetProductByIdUseCaseImpl(
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
        val argId = 0L

        coEvery { repository.getProductById(argId) } returns expectedResult

        /* Act */
        runTest {
            val result = getProductByIdUseCase(argId)
            /* Assert */
            assertEquals(expectedResult, result)
        }
    }
}
