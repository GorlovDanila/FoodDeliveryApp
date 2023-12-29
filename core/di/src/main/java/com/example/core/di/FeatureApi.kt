package com.example.core.di

import androidx.annotation.RestrictTo
import com.example.core.di.dsl.AbstractFeatureApi
import com.example.core.di.dsl.ApiDSL
import com.example.core.di.dsl.ApiDefinition
import org.koin.core.component.getScopeName
import org.koin.core.qualifier.Qualifier

abstract class FeatureApi : AbstractFeatureApi() {

    final override val qualifier: Qualifier = getScopeName()

    override val isLibrary: Boolean = false

    final override var dependencies: List<AbstractFeatureApi> = emptyList()

    protected abstract fun ApiDSL.definitions()

    @RestrictTo(RestrictTo.Scope.TESTS)
    fun getApiDefinitions(): List<ApiDefinition> =
        ApiDSL().apply { definitions() }.definitions
}

abstract class LibraryApi : FeatureApi() {

    override val isLibrary: Boolean = true
}