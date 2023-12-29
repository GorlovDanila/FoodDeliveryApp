package com.example.core.di

import com.example.core.di.dsl.AbstractFeatureApi
import org.koin.core.Koin

fun <F : AbstractFeatureApi> Koin.getFeatureApiManager(feature: F): FeatureApiManager<F> {
    val scopeManager = getScopeOrNull(feature.qualifier.value)?.get<FeatureApiManager<F>>()
    if (scopeManager != null) return scopeManager
    return FeatureApiManager(this, feature)
}