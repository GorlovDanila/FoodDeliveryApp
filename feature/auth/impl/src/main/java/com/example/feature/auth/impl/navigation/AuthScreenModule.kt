package com.example.feature.auth.impl.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.example.core.navigation.SharedScreen
import com.example.feature.auth.impl.presentation.ui.AuthorizationScreen

val featureAuthorizationScreenModule = screenModule {
    register<SharedScreen.AuthorizationScreen> {
        AuthorizationScreen()
    }
}
