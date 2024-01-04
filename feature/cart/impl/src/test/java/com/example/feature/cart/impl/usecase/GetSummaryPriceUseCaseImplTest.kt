package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.GetSummaryPriceUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetSummaryPriceUseCaseImplTest {

    @MockK
    lateinit var repository: ProductRepository

    private lateinit var getSummaryPriceUseCase: GetSummaryPriceUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
       getSummaryPriceUseCase = GetSummaryPriceUseCaseImpl(
           repository = repository
       )
    }

    @Test
    fun success() {
        /* Arrange */
        val expectedResult = "10"

        coEvery { repository.summaryPrice() } returns expectedResult

        /* Act */
        runTest {
            val result = getSummaryPriceUseCase()
            /* Assert */
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun fail() {
        /* Arrange */
        val expectedResult = "0"

        coEvery { repository.summaryPrice() } returns expectedResult

        /* Act */
        runTest {
            val result = getSummaryPriceUseCase()
            /* Assert */
            assertEquals(expectedResult, result)
        }
    }
}
