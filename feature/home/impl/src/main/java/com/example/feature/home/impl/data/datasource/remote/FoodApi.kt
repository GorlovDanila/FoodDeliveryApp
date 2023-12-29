package com.example.feature.home.impl.data.datasource.remote

import com.example.feature.home.impl.data.datasource.remote.response.FoodListResponse
import com.example.feature.home.impl.data.datasource.remote.response.FoodResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface FoodApi {

    @GET("food")
    suspend fun getFoodList(): FoodListResponse

    @GET("food/{id}")
    suspend fun getFoodById(
        @Path("id") id: Long,
    ): FoodResponse
}