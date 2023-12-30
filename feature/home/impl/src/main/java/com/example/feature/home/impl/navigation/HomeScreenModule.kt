package com.example.feature.home.impl.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.example.core.navigation.SharedScreen
import com.example.feature.home.impl.presentation.ui.HomeScreen

val featureHomeScreenModule = screenModule {
    register<SharedScreen.HomeScreen> {
        HomeScreen()
    }
}