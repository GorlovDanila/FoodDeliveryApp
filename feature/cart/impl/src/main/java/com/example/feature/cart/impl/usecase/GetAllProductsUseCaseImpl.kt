package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.GetAllProductsUseCase

class GetAllProductsUseCaseImpl(
    private val repository: ProductRepository
) : GetAllProductsUseCase {
    override suspend fun invoke() : List<ProductInfo> = repository.getAllProducts()
}
