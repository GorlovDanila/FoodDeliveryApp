package com.example.feature.auth.impl.usecase

import com.example.feature.auth.api.model.User
import com.example.feature.auth.api.repository.UserRepository
import com.example.feature.auth.api.usecase.GetCurrentUserUseCase

class GetCurrentUserUseCaseImpl(
    private val repository: UserRepository
) : GetCurrentUserUseCase {
    override suspend fun invoke(): User = repository.getCurrentUser()
}
