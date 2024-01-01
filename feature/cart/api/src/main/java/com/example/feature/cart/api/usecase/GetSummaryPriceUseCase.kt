package com.example.feature.cart.api.usecase

interface GetSummaryPriceUseCase {
    suspend operator fun invoke() : String
}
