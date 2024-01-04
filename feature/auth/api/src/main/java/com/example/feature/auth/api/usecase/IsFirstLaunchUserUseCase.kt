package com.example.feature.auth.api.usecase

interface IsFirstLaunchUserUseCase {
    suspend operator fun invoke(): Boolean?
}
