package com.example.feature.profile.impl.di

import com.example.feature.profile.impl.presentation.presenter.ProfileScreenModel
import org.koin.dsl.module

val profileModule = module {
    factory {
        ProfileScreenModel(
            getCurrentUserUseCase = get(),
            onSignOutUserUseCase = get(),
        )
    }
}
