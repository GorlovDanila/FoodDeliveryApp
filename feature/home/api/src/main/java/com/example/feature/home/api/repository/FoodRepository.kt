package com.example.feature.home.api.repository

import com.example.feature.home.api.model.FoodInfo
import com.example.feature.home.api.model.FoodListInfo

interface FoodRepository {
    suspend fun getFoodById(id: Long): FoodInfo

    suspend fun getFoodList(): FoodListInfo
}
