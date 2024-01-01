package com.example.feature.cart.api.usecase

import com.example.feature.cart.api.model.ProductInfo

interface GetAllProductsUseCase {
    suspend operator fun invoke() : List<ProductInfo>
}
