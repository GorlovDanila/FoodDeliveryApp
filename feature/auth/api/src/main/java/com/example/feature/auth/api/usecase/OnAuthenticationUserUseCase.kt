package com.example.feature.auth.api.usecase

import androidx.datastore.preferences.core.Preferences

interface OnAuthenticationUserUseCase {
    suspend operator fun invoke(): Preferences
}