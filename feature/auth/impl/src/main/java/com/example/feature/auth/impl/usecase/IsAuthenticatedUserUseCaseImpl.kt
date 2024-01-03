package com.example.feature.auth.impl.usecase

import com.example.feature.auth.api.repository.UserRepository
import com.example.feature.auth.api.usecase.IsAuthenticatedUserUseCase

class IsAuthenticatedUserUseCaseImpl(
    private val repository: UserRepository
) : IsAuthenticatedUserUseCase {
    override suspend fun invoke(): Boolean? = repository.isAuthenticated()
}
