package com.example.feature.home.impl.data.datasource.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("price")
    val price: String,
    @SerialName("weight")
    val weight: String,
    @SerialName("recipe")
    val recipe: String,
    @SerialName("kcal")
    val kcal: String,
    @SerialName("proteins")
    val proteins: String,
    @SerialName("fats")
    val fats: String,
    @SerialName("carbohydrates")
    val carbohydrates: String,
)
