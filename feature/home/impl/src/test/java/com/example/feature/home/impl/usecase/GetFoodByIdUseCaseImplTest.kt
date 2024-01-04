package com.example.feature.home.impl.usecase

import com.example.feature.home.api.model.FoodInfo
import com.example.feature.home.api.repository.FoodRepository
import com.example.feature.home.api.usecase.GetFoodByIdUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import org.junit.Assert.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetFoodByIdUseCaseImplTest {

    @MockK
    lateinit var repository: FoodRepository

    private lateinit var getFoodByIdUseCase: GetFoodByIdUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getFoodByIdUseCase = GetFoodByIdUseCaseImpl(
            repository = repository
        )
    }

    @Test
    fun success() {
        /* Arrange */
        val expectedResult = FoodInfo(
            id = 0,
            title = "test",
            imageUrl = "",
            price = "",
            weight = "",
            recipe = "",
            kcal = "",
            proteins = "",
            fats = "",
            carbohydrates = "",
        )

        coEvery { repository.getFoodById(id = 0) } returns expectedResult

        /* Act */
        runTest {
            val result = getFoodByIdUseCase(0)
            /* Assert */
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun error() {
        /* Arrange */
        val expectedResult = FoodInfo(
            id = 0,
            title = "",
            imageUrl = "",
            price = "",
            weight = "",
            recipe = "",
            kcal = "",
            proteins = "",
            fats = "",
            carbohydrates = "",
        )

        coEvery { repository.getFoodById(id = 0) } returns expectedResult

        /* Act */
        runTest {
            val result = getFoodByIdUseCase(0)
            /* Assert */
            assertEquals(expectedResult, result)
        }
    }
}
