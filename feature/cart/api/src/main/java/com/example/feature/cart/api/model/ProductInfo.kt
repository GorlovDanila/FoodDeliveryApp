package com.example.feature.cart.api.model

data class ProductInfo(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val price: String,
    val count: Int,
)
