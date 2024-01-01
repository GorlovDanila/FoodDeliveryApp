package com.example.feature.home.impl.data.mapper

import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.home.api.model.FoodInfo
import com.example.feature.home.impl.data.datasource.remote.response.FoodResponse

fun FoodResponse.toFoodInfo() : FoodInfo = FoodInfo(
    id = id,
    title = title,
    imageUrl = imageUrl,
    price = price,
    weight = weight,
    recipe = recipe,
    kcal = kcal,
    proteins = proteins,
    fats = fats,
    carbohydrates = carbohydrates
)

fun FoodInfo.toProductInfo() : ProductInfo = ProductInfo(
    id = id,
    title = title,
    imageUrl = imageUrl,
    price = price,
    count = 1
)
