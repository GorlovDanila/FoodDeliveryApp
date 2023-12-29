package com.example.feature.profile.impl.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.example.core.navigation.SharedScreen
import com.example.feature.profile.impl.presentation.ProfileScreen

val featureProfileScreenModule = screenModule {
    register<SharedScreen.ProfileScreen> {
        ProfileScreen()
    }
}