package com.example.feature.cart.api.usecase

import com.example.feature.cart.api.model.ProductInfo

interface GetProductByIdUseCase {
    suspend operator fun invoke(id: Long) : ProductInfo
}
