package com.example.feature.auth.impl.usecase

import androidx.datastore.preferences.core.Preferences
import com.example.feature.auth.api.repository.UserRepository
import com.example.feature.auth.api.usecase.OnFirstLaunchUserUseCase

class OnFirstLaunchUserUseCaseImpl(
    private val repository: UserRepository
) : OnFirstLaunchUserUseCase {
    override suspend fun invoke(): Preferences = repository.onFirstLaunch()
}
