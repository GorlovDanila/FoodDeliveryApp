package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.DeleteAllProductsUseCase

class DeleteAllProductsUseCaseImpl(
    private val repository: ProductRepository
) : DeleteAllProductsUseCase {
    override suspend fun invoke() = repository.deleteAllProducts()
}
