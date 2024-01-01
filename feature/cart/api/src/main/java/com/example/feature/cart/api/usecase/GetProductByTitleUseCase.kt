package com.example.feature.cart.api.usecase

import com.example.feature.cart.api.model.ProductInfo

interface GetProductByTitleUseCase {
    suspend operator fun invoke(title: String) : ProductInfo?
}
