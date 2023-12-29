package com.example.feature.search.impl.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.example.core.navigation.SharedScreen
import com.example.feature.search.impl.presentation.SearchScreen

val featureSearchScreenModule = screenModule {
    register<SharedScreen.SearchScreen> {
        SearchScreen()
    }
}