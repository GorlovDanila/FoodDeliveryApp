package com.example.feature.home.impl.data.mapper

import com.example.feature.home.api.model.FoodListInfo
import com.example.feature.home.impl.data.datasource.remote.response.FoodListResponse

fun FoodListResponse.toFoodListInfo() : FoodListInfo = FoodListInfo(
    listFood.map { it.toFoodInfo() }
)