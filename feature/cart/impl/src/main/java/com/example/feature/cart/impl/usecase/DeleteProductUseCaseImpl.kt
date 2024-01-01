package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.DeleteProductUseCase

class DeleteProductUseCaseImpl(
    private val repository: ProductRepository
) : DeleteProductUseCase {
    override suspend fun invoke(productInfo: ProductInfo) = repository.deleteProduct(productInfo)
}
