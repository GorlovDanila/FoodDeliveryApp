package com.example.feature.auth.api.usecase

interface IsAuthenticatedUserUseCase {
    suspend operator fun invoke(): Boolean?
}