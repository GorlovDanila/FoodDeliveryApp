package com.example.feature.auth.impl.usecase

import androidx.datastore.preferences.core.Preferences
import com.example.feature.auth.api.repository.UserRepository
import com.example.feature.auth.api.usecase.OnAuthenticationUserUseCase

class OnAuthenticationUserUseCaseImpl(
    private val repository: UserRepository
) : OnAuthenticationUserUseCase {
    override suspend fun invoke(): Preferences = repository.onAuthentication()
}
