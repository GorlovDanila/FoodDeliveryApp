package com.example.feature.home.impl.data

import com.example.feature.home.api.model.FoodInfo
import com.example.feature.home.api.model.FoodListInfo
import com.example.feature.home.api.repository.FoodRepository
import com.example.feature.home.impl.data.datasource.remote.FoodApi
import com.example.feature.home.impl.data.mapper.toFoodInfo
import com.example.feature.home.impl.data.mapper.toFoodListInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class FoodRepositoryImpl(
    private val api: FoodApi,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO,
): FoodRepository {
    override suspend fun getFoodById(id: Long): FoodInfo =
        withContext(dispatcherIO) { api.getFoodById(id).toFoodInfo() }

    override suspend fun getFoodList(): FoodListInfo =
        withContext(dispatcherIO) { api.getFoodList().toFoodListInfo() }

}
