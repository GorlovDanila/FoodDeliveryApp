package com.example.core.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen : ScreenProvider {
    data object CartScreen : SharedScreen()
    data object SearchScreen : SharedScreen()
}
