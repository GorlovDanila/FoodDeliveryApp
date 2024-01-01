package com.example.feature.cart.impl.usecase

import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.GetSummaryPriceUseCase

class GetSummaryPriceUseCaseImpl(
    private val repository: ProductRepository
) : GetSummaryPriceUseCase {
    override suspend fun invoke(): String = repository.summaryPrice()
}
