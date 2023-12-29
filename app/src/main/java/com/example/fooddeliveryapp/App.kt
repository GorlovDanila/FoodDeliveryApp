package com.example.fooddeliveryapp

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.example.feature.cart.impl.navigation.featureShoppingCartScreenModule
import com.example.feature.profile.impl.navigation.featureProfileScreenModule
import com.example.feature.search.impl.navigation.featureSearchScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            featureShoppingCartScreenModule()
            featureSearchScreenModule()
            featureProfileScreenModule()
        }

        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(

            )
        }
    }
}
