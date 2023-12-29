package com.example.fooddeliveryapp

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.example.feature.cart.impl.navigation.featureShoppingCartScreenModule
import com.example.feature.profile.impl.navigation.featureProfileScreenModule
import com.example.feature.search.impl.navigation.featureSearchScreenModule
import com.example.core.di.FeatureApiManager
import com.example.core.di.FeatureImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import timber.log.Timber


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

        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                module {
                    single(FeatureApiManager.FEATURE_MAP_QUALIFIER) { features().associateBy { it.qualifier } }
                }
            )
        }
    }
}

fun features(): List<FeatureImpl> =
    listOf(

    )
