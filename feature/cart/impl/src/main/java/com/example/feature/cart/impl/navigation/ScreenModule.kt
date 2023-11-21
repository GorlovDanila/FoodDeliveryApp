package com.example.feature.cart.impl.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.example.core.navigation.SharedScreen
import com.example.feature.cart.impl.presentation.ShoppingCartScreen

val featureCartScreenModule = screenModule {
    register<SharedScreen.CartScreen> {
        ShoppingCartScreen()
    }
}