package com.example.feature.home.impl.usecase

import com.example.feature.home.api.model.Content
import com.example.feature.home.api.repository.HomeRepository
import com.example.feature.home.api.usecase.GetHomeContentUseCase

internal class GetHomeContentUseCaseImpl(
    val repository: HomeRepository
): GetHomeContentUseCase {
    override suspend fun invoke(id: Int): Content {
        return repository.getContent(id)
    }
}