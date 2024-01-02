package com.example.feature.auth.impl.usecase

import androidx.datastore.preferences.core.Preferences
import com.example.feature.auth.api.repository.UserRepository
import com.example.feature.auth.api.usecase.OnSignOutUserUseCase

class OnSignOutUseCaseImpl(
    private val repository: UserRepository
) : OnSignOutUserUseCase {
    override suspend fun invoke(): Preferences = repository.onSignOut()
}
