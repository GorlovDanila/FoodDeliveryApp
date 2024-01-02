package com.example.feature.auth.api.usecase

import androidx.datastore.preferences.core.Preferences

interface SaveCurrentUserUseCase {
    suspend operator fun invoke(login: String, password: String): Preferences
}