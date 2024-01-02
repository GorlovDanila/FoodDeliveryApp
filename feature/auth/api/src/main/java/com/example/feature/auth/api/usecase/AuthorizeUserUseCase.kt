package com.example.feature.auth.api.usecase

import com.example.feature.auth.api.model.UserResponseCode

interface AuthorizeUserUseCase {
    suspend operator fun invoke(login: String, password: String): UserResponseCode
}