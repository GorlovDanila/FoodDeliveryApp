package com.example.feature.home.impl.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class FoodListResponse(
    @SerializedName("foods")
    val listFood: List<FoodResponse>
)