package com.example.fooddeliveryapp

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.example.feature.cart.impl.navigation.featureShoppingCartScreenModule
import com.example.feature.profile.impl.navigation.featureProfileScreenModule
import com.example.feature.search.impl.navigation.featureSearchScreenModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            featureShoppingCartScreenModule()
            featureSearchScreenModule()
            featureProfileScreenModule()
        }
    }
}