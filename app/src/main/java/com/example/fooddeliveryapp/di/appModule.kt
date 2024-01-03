package com.example.fooddeliveryapp.di

import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import org.koin.dsl.module

val appModule = module {
    single {
        provideFirebaseAnalytics()
    }
}

private fun provideFirebaseAnalytics() : FirebaseAnalytics = Firebase.analytics
