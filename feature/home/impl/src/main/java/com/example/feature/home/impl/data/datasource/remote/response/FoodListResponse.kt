package com.example.feature.home.impl.data.datasource.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class FoodListResponse(
    val listFood: List<FoodResponse>
)