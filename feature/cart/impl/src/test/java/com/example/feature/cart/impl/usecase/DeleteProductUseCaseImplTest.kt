package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.DeleteProductUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DeleteProductUseCaseImplTest {

    @MockK
    lateinit var repository: ProductRepository

    private lateinit var deleteProductUseCase: DeleteProductUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        deleteProductUseCase = DeleteProductUseCaseImpl(
            repository = repository
        )
    }

    @Test
    fun success() {
        /* Arrange */
        val expectedResult = Unit
        val testArg = ProductInfo(
            id = 0,
            title = "test",
            imageUrl = "",
            price = "",
            count = 0
        )

        coEvery { repository.deleteProduct(testArg) } returns expectedResult

        /* Act */
        runTest {
            val result = deleteProductUseCase(testArg)
            /* Assert */
            assertEquals(expectedResult, result)
        }
    }
}
