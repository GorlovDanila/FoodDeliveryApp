package com.example.feature.home.impl.usecase

import com.example.feature.home.api.model.FoodInfo
import com.example.feature.home.api.repository.FoodRepository
import com.example.feature.home.api.usecase.GetFoodByIdUseCase

class GetFoodByIdUseCaseImpl(
    private val repository: FoodRepository
) : GetFoodByIdUseCase {
    override suspend fun invoke(id: Long): FoodInfo = repository.getFoodById(id)
}