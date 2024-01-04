package com.example.feature.auth.impl.data.datasource.remote.request

import com.example.feature.auth.api.model.User
import com.example.feature.auth.impl.data.datasource.remote.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

internal interface UserApi {

    @POST("user/registration")
    suspend fun registrationUser(
        @Body user: User
    ): UserResponse

    @POST("user/authorization")
    suspend fun authorizeUser(
        @Body user: User,
    ): UserResponse
}
