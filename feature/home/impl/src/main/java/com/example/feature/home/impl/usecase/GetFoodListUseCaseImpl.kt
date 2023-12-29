package com.example.feature.home.impl.usecase

import com.example.feature.home.api.model.FoodListInfo
import com.example.feature.home.api.repository.FoodRepository
import com.example.feature.home.api.usecase.GetFoodListUseCase

class GetFoodListUseCaseImpl(
    val repository: FoodRepository
) : GetFoodListUseCase {
    override suspend fun invoke(): FoodListInfo = repository.getFoodList()
}