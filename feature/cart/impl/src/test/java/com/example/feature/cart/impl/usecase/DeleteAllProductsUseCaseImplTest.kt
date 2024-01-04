package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.DeleteAllProductsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DeleteAllProductsUseCaseImplTest {

    @MockK
    lateinit var repository: ProductRepository

    private lateinit var deleteAllProductsUseCase: DeleteAllProductsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        deleteAllProductsUseCase = DeleteAllProductsUseCaseImpl(
            repository = repository
        )
    }

    @Test
    fun success() {
        /* Arrange */
        val expectedResult = Unit

        coEvery { repository.deleteAllProducts() } returns expectedResult

        /* Act */
        runTest {
            val result = deleteAllProductsUseCase()
            /* Assert */
            assertEquals(expectedResult, result)
        }
    }
}
