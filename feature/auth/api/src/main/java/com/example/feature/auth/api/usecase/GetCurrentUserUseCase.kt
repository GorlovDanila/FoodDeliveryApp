package com.example.feature.auth.api.usecase

import com.example.feature.auth.api.model.User

interface GetCurrentUserUseCase {
    suspend operator fun invoke(): User
}