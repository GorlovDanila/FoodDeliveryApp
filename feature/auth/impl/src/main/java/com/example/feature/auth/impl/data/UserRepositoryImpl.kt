package com.example.feature.auth.impl.data

import androidx.datastore.preferences.core.Preferences
import com.example.feature.auth.api.model.User
import com.example.feature.auth.api.model.UserResponseCode
import com.example.feature.auth.api.repository.UserRepository
import com.example.feature.auth.impl.data.datasource.local.AuthDataStore
import com.example.feature.auth.impl.data.datasource.remote.request.UserApi
import com.example.feature.auth.impl.data.mapper.toUserResponseCode
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class UserRepositoryImpl(
    private val api: UserApi,
    private val authDataStore: AuthDataStore,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO,
) : UserRepository {
    override suspend fun register(user: User): UserResponseCode =
        withContext(dispatcherIO) { api.registrationUser(user).toUserResponseCode() }

    override suspend fun authorize(user: User): UserResponseCode =
        withContext(dispatcherIO) { api.authorizeUser(user).toUserResponseCode() }

    override suspend fun onAuthentication(): Preferences =
        withContext(dispatcherIO) { authDataStore.onAuthentication() }

    override suspend fun isAuthenticated(): Boolean? =
        withContext(dispatcherIO) { authDataStore.isAuthenticated() }

    override suspend fun onFirstLaunch(): Preferences =
        withContext(dispatcherIO) { authDataStore.onFirstLaunch() }

    override suspend fun isFirstLaunch(): Boolean? =
        withContext(dispatcherIO) { authDataStore.isFirstLaunch() }

    override suspend fun saveCurrentUser(login: String, password: String): Preferences =
        withContext(dispatcherIO) {
            authDataStore.onAuthentication()
            authDataStore.saveCurrentUserLogin(login)
            authDataStore.saveCurrentUserPassword(password)
        }

    override suspend fun getCurrentUser(): User =
        withContext(dispatcherIO) {
            User(
                authDataStore.getCurrentUserLogin() ?: "",
                authDataStore.getCurrentUserPassword() ?: "",
            )
        }

    override suspend fun onSignOut(): Preferences =
        withContext(dispatcherIO) { authDataStore.onSignOut() }

}
