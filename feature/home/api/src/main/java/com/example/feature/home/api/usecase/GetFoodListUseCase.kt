package com.example.feature.home.api.usecase

import com.example.feature.home.api.model.FoodListInfo

interface GetFoodListUseCase {
    suspend operator fun invoke(): FoodListInfo
}
