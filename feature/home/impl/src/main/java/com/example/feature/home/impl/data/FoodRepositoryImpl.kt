package com.example.feature.home.impl.data

import com.example.feature.home.api.model.FoodInfo
import com.example.feature.home.api.model.FoodListInfo
import com.example.feature.home.api.repository.FoodRepository
import com.example.feature.home.impl.data.datasource.remote.FoodApi
import com.example.feature.home.impl.data.mapper.toFoodInfo
import com.example.feature.home.impl.data.mapper.toFoodListInfo

internal class FoodRepositoryImpl(
    private val api: FoodApi
): FoodRepository {
    override suspend fun getFoodById(id: Long): FoodInfo = api.getFoodById(id).toFoodInfo()

    override suspend fun getFoodList(): FoodListInfo = api.getFoodList().toFoodListInfo()

}