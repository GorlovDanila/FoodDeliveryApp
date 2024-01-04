package com.example.feature.auth.api.usecase

import androidx.datastore.preferences.core.Preferences

interface OnFirstLaunchUserUseCase {
    suspend operator fun invoke(): Preferences
}
