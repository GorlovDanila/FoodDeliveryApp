package com.example.fooddeliveryapp

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.example.feature.cart.impl.navigation.featureCartScreenModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            featureCartScreenModule()
        }
    }
}
