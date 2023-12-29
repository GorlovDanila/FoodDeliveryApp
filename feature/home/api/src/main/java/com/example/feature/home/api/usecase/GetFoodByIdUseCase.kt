package com.example.feature.home.api.usecase

import com.example.feature.home.api.model.FoodInfo

interface GetFoodByIdUseCase {
    suspend operator fun invoke(id: Long): FoodInfo
}