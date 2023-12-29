package com.example.feature.home.api.model


data class FoodInfo(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val price: String,
    val weight: String,
    val recipe: String,
    val kcal: String,
    val proteins: String,
    val fats: String,
    val carbohydrates: String,
)