package com.example.feature.auth.impl.usecase

import com.example.feature.auth.api.repository.UserRepository
import com.example.feature.auth.api.usecase.IsFirstLaunchUserUseCase

class IsFirstLaunchUserUseCaseImpl(
    private val repository: UserRepository
) : IsFirstLaunchUserUseCase {
    override suspend fun invoke(): Boolean? = repository.isFirstLaunch()
}
