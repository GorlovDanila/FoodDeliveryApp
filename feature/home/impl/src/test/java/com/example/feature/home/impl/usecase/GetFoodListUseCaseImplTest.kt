package com.example.feature.home.impl.usecase

import com.example.feature.home.api.model.FoodInfo
import com.example.feature.home.api.model.FoodListInfo
import com.example.feature.home.api.repository.FoodRepository
import com.example.feature.home.api.usecase.GetFoodListUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetFoodListUseCaseImplTest {

    @MockK
    lateinit var repository: FoodRepository

    private lateinit var getFoodListUseCase: GetFoodListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getFoodListUseCase = GetFoodListUseCaseImpl(
            repository = repository
        )
    }

    @Test
    fun success() {
        /* Arrange */
        val expectedResult = FoodListInfo(
            listFood = listOf(
                FoodInfo(
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
            )
        )

        coEvery { repository.getFoodList() } returns expectedResult

        /* Act */
        runTest {
            val result = getFoodListUseCase()
            /* Assert */
            Assert.assertEquals(expectedResult, result)
        }
    }

    @Test
    fun error() {
        /* Arrange */
        val expectedResult = FoodListInfo(
            listFood = null
        )

        coEvery { repository.getFoodList() } returns expectedResult

        /* Act */
        runTest {
            val result = getFoodListUseCase()
            /* Assert */
            Assert.assertEquals(expectedResult, result)
        }
    }
}
