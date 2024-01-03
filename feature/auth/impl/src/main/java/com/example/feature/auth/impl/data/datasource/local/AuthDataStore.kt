package com.example.feature.auth.impl.data.datasource.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class AuthDataStore(
    private val context: Context
) {
    suspend fun isAuthenticated(): Boolean? = context.dataStore.data.map {
        it.get(key = IS_AUTHENTICATED_KEY)
    }.flowOn(Dispatchers.IO)
        .first()

    suspend fun onAuthentication(): Preferences = context.dataStore.edit {
        it[IS_AUTHENTICATED_KEY] = true
    }

    suspend fun getCurrentUserLogin(): String? = context.dataStore.data.map {
        it.get(key = CURRENT_USER_LOGIN_KEY)
    }.flowOn(Dispatchers.IO)
        .first()

    suspend fun saveCurrentUserLogin(login: String): Preferences = context.dataStore.edit {
        it[CURRENT_USER_LOGIN_KEY] = login
    }

    suspend fun getCurrentUserPassword(): String? = context.dataStore.data.map {
        it.get(key = CURRENT_USER_PASSWORD_KEY)
    }.flowOn(Dispatchers.IO)
        .first()

    suspend fun saveCurrentUserPassword(password: String): Preferences = context.dataStore.edit {
        it[CURRENT_USER_PASSWORD_KEY] = password
    }

    suspend fun onSignOut(): Preferences = context.dataStore.edit {
        it[IS_AUTHENTICATED_KEY] = false
        it[CURRENT_USER_LOGIN_KEY] = ""
        it[CURRENT_USER_PASSWORD_KEY] = ""
    }

    private companion object {
        const val DATASTORE_NAME = "CURRENT_USER_DATASTORE"
        const val IS_AUTHENTICATED_KEY_NAME = "IS_AUTHENTICATED"
        const val CURRENT_USER_LOGIN_KEY_NAME = "USER_LOGIN"
        const val CURRENT_USER_PASSWORD_KEY_NAME = "USER_PASSWORD"

        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)
        val IS_AUTHENTICATED_KEY = booleanPreferencesKey(name = IS_AUTHENTICATED_KEY_NAME)
        val CURRENT_USER_LOGIN_KEY = stringPreferencesKey(name = CURRENT_USER_LOGIN_KEY_NAME)
        val CURRENT_USER_PASSWORD_KEY = stringPreferencesKey(name = CURRENT_USER_PASSWORD_KEY_NAME)
    }
}
