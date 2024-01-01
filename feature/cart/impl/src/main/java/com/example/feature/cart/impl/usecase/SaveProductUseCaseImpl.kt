package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.SaveProductUseCase

class SaveProductUseCaseImpl(
    private val repository: ProductRepository
) : SaveProductUseCase {
    override suspend fun invoke(productInfo: ProductInfo) = repository.saveProduct(productInfo)
}
