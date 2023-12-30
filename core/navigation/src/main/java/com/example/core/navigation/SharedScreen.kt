package com.example.core.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen : ScreenProvider {
    data object CartScreen : SharedScreen()
    data object SearchScreen : SharedScreen()
    data object ProfileScreen : SharedScreen()
    data object HomeScreen : SharedScreen()
}
