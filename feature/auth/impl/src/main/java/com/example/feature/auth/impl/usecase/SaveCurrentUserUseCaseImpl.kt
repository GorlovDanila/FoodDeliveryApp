package com.example.feature.auth.impl.usecase

import androidx.datastore.preferences.core.Preferences
import com.example.feature.auth.api.repository.UserRepository
import com.example.feature.auth.api.usecase.SaveCurrentUserUseCase

class SaveCurrentUserUseCaseImpl(
    private val repository: UserRepository
): SaveCurrentUserUseCase {
    override suspend fun invoke(login: String, password: String): Preferences =
        repository.saveCurrentUser(login, password)
}
