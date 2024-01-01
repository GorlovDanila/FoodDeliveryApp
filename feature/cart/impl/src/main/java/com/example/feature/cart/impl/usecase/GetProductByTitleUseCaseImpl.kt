package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.GetProductByTitleUseCase

class GetProductByTitleUseCaseImpl(
    private val repository: ProductRepository
) : GetProductByTitleUseCase {
    override suspend fun invoke(title: String): ProductInfo? = repository.getProductByTitle(title)
}
