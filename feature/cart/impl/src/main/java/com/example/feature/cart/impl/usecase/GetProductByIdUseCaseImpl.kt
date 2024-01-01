package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.model.ProductInfo
import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.GetProductByIdUseCase

class GetProductByIdUseCaseImpl(
    private val repository: ProductRepository
) : GetProductByIdUseCase {
    override suspend fun invoke(id: Long) : ProductInfo = repository.getProductById(id)
}
