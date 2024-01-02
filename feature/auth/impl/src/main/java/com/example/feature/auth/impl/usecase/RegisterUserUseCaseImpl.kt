package com.example.feature.auth.impl.usecase

import com.example.feature.auth.api.model.User
import com.example.feature.auth.api.model.UserResponseCode
import com.example.feature.auth.api.repository.UserRepository
import com.example.feature.auth.api.usecase.RegisterUserUseCase

class RegisterUserUseCaseImpl(
    private val repository: UserRepository
) : RegisterUserUseCase {
    override suspend fun invoke(login: String, password: String): UserResponseCode =
        repository.register(User(login, password))
}
