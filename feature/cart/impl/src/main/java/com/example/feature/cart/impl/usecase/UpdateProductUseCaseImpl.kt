package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.UpdateProductUseCase

class UpdateProductUseCaseImpl(
    private val repository: ProductRepository
) : UpdateProductUseCase {
    override suspend fun invoke(productInfo: ProductInfo) = repository.updateProduct(productInfo)
}
