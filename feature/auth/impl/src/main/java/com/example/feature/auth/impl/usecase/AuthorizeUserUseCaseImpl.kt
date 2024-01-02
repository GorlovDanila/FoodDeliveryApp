package com.example.feature.auth.impl.usecase

import com.example.feature.auth.api.model.User
import com.example.feature.auth.api.model.UserResponseCode
import com.example.feature.auth.api.repository.UserRepository
import com.example.feature.auth.api.usecase.AuthorizeUserUseCase

class AuthorizeUserUseCaseImpl(
    private val repository: UserRepository
) : AuthorizeUserUseCase {
    override suspend fun invoke(login: String, password: String): UserResponseCode =
        repository.authorize(User(login, password))
}
