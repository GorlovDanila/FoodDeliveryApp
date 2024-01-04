package com.example.feature.auth.impl.di

import android.content.Context
import com.example.feature.auth.api.repository.UserRepository
import com.example.feature.auth.api.usecase.AuthorizeUserUseCase
import com.example.feature.auth.api.usecase.GetCurrentUserUseCase
import com.example.feature.auth.api.usecase.IsAuthenticatedUserUseCase
import com.example.feature.auth.api.usecase.IsFirstLaunchUserUseCase
import com.example.feature.auth.api.usecase.OnAuthenticationUserUseCase
import com.example.feature.auth.api.usecase.OnFirstLaunchUserUseCase
import com.example.feature.auth.api.usecase.OnSignOutUserUseCase
import com.example.feature.auth.api.usecase.RegisterUserUseCase
import com.example.feature.auth.api.usecase.SaveCurrentUserUseCase
import com.example.feature.auth.impl.data.UserRepositoryImpl
import com.example.feature.auth.impl.data.datasource.local.AuthDataStore
import com.example.feature.auth.impl.data.datasource.remote.request.UserApi
import com.example.feature.auth.impl.presentation.presenter.AuthorizationScreenModel
import com.example.feature.auth.impl.presentation.presenter.RegistrationScreenModel
import com.example.feature.auth.impl.usecase.AuthorizeUserUseCaseImpl
import com.example.feature.auth.impl.usecase.GetCurrentUserUseCaseImpl
import com.example.feature.auth.impl.usecase.IsAuthenticatedUserUseCaseImpl
import com.example.feature.auth.impl.usecase.IsFirstLaunchUserUseCaseImpl
import com.example.feature.auth.impl.usecase.OnAuthenticationUserUseCaseImpl
import com.example.feature.auth.impl.usecase.OnFirstLaunchUserUseCaseImpl
import com.example.feature.auth.impl.usecase.OnSignOutUseCaseImpl
import com.example.feature.auth.impl.usecase.RegisterUserUseCaseImpl
import com.example.feature.auth.impl.usecase.SaveCurrentUserUseCaseImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val authModule = module {
    single {
        provideUserApi(
            retrofit = get()
        )
    }
    single {
        provideAuthDataStore(
            context = get()
        )
    }
    single {
        provideUserRepository(
            userApi = get(),
            authDataStore = get(),
        )
    }
    single {
        provideRegisterUserUseCase(
            userRepository = get()
        )
    }
    single {
        provideAuthorizeUserUseCase(
            userRepository = get()
        )
    }
    single {
        provideGetCurrentUserUseCase(
            userRepository = get()
        )
    }
    single {
        provideOnSignOutUserUseCase(
            userRepository = get()
        )
    }
    single {
        provideSaveCurrentUserUseCase(
            userRepository = get()
        )
    }
    single {
        provideOnAuthenticationUserUseCase(
            userRepository = get()
        )
    }
    single {
        provideIsAuthenticatedUserUseCase(
            userRepository = get()
        )
    }
    single {
        provideOnFirstLaunchUserUseCase(
            userRepository = get()
        )
    }
    single {
        provideIsFirstLaunchUserUseCase(
            userRepository = get()
        )
    }

    factory {
        RegistrationScreenModel(
            registerUserUseCase = get(),
            isAuthenticatedUserUseCase = get(),
            isFirstLaunchUserUseCase = get(),
        )
    }
    factory {
        AuthorizationScreenModel(
            authorizeUserUseCase = get(),
            saveCurrentUserUseCase = get(),
            onAuthenticationUserUseCase = get(),
            getCurrentUserUseCase = get(),
        )
    }
}

private fun provideUserApi(
    retrofit: Retrofit
): UserApi = retrofit.create(UserApi::class.java)

private fun provideAuthDataStore(
    context: Context,
): AuthDataStore = AuthDataStore(context)

private fun provideUserRepository(
    userApi: UserApi,
    authDataStore: AuthDataStore,
): UserRepository = UserRepositoryImpl(userApi, authDataStore)

private fun provideRegisterUserUseCase(
    userRepository: UserRepository
): RegisterUserUseCase = RegisterUserUseCaseImpl(userRepository)

private fun provideAuthorizeUserUseCase(
    userRepository: UserRepository
): AuthorizeUserUseCase = AuthorizeUserUseCaseImpl(userRepository)

private fun provideGetCurrentUserUseCase(
    userRepository: UserRepository
): GetCurrentUserUseCase = GetCurrentUserUseCaseImpl(userRepository)

private fun provideOnSignOutUserUseCase(
    userRepository: UserRepository
): OnSignOutUserUseCase = OnSignOutUseCaseImpl(userRepository)

private fun provideSaveCurrentUserUseCase(
    userRepository: UserRepository
): SaveCurrentUserUseCase = SaveCurrentUserUseCaseImpl(userRepository)

private fun provideOnAuthenticationUserUseCase(
    userRepository: UserRepository
): OnAuthenticationUserUseCase = OnAuthenticationUserUseCaseImpl(userRepository)

private fun provideIsAuthenticatedUserUseCase(
    userRepository: UserRepository
): IsAuthenticatedUserUseCase = IsAuthenticatedUserUseCaseImpl(userRepository)

private fun provideOnFirstLaunchUserUseCase(
    userRepository: UserRepository
): OnFirstLaunchUserUseCase = OnFirstLaunchUserUseCaseImpl(userRepository)

private fun provideIsFirstLaunchUserUseCase(
    userRepository: UserRepository
): IsFirstLaunchUserUseCase = IsFirstLaunchUserUseCaseImpl(userRepository)
