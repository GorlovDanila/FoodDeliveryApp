package com.example.feature.auth.api.usecase

import androidx.datastore.preferences.core.Preferences

interface OnSignOutUserUseCase {
    suspend operator fun invoke(): Preferences
}