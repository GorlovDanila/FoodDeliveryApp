package com.example.feature.auth.impl.data.mapper

import com.example.feature.auth.api.model.UserResponseCode
import com.example.feature.auth.impl.data.datasource.remote.response.UserResponse

fun UserResponse.toUserResponseCode(): UserResponseCode = UserResponseCode(code = status)
