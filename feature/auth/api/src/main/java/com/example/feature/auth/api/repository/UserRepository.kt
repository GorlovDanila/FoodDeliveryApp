package com.example.feature.auth.api.repository

import androidx.datastore.preferences.core.Preferences
import com.example.feature.auth.api.model.User
import com.example.feature.auth.api.model.UserResponseCode

interface UserRepository {
    suspend fun register(user: User) : UserResponseCode

    suspend fun authorize(user: User) : UserResponseCode

    suspend fun onAuthentication() : Preferences

    suspend fun isAuthenticated() : Boolean?

    suspend fun onFirstLaunch() : Preferences

    suspend fun isFirstLaunch() : Boolean?

    suspend fun saveCurrentUser(login: String, password: String) : Preferences

    suspend fun getCurrentUser() : User

    suspend fun onSignOut() : Preferences
}
