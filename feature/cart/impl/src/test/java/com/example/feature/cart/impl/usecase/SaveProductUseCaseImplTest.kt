package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.SaveProductUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SaveProductUseCaseImplTest {

    @MockK
    lateinit var repository: ProductRepository

    private lateinit var saveProductUseCase: SaveProductUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        saveProductUseCase = SaveProductUseCaseImpl(
            repository = repository
        )
    }

    @Test
    fun success() {
        /* Arrange */
        val expectedResult = Unit
        val productInfo =
            ProductInfo(
                id = 0,
                title = "test",
                imageUrl = "",
                price = "",
                count = 0
            )

        coEvery { repository.saveProduct(productInfo) } returns expectedResult

        /* Act */
        runTest {
            val result = saveProductUseCase(productInfo)
            /* Assert */
            assertEquals(expectedResult, result)
        }
    }
}
