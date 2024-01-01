package com.example.feature.cart.api.usecase

import com.example.feature.cart.api.model.ProductInfo

interface DeleteProductUseCase {
    suspend operator fun invoke(productInfo: ProductInfo)
}
