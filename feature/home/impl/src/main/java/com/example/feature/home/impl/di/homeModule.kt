package com.example.feature.home.impl.di

import com.example.feature.home.api.repository.FoodRepository
import com.example.feature.home.api.usecase.GetFoodByIdUseCase
import com.example.feature.home.api.usecase.GetFoodListUseCase
import com.example.feature.home.impl.data.FoodRepositoryImpl
import com.example.feature.home.impl.data.datasource.remote.request.FoodApi
import com.example.feature.home.impl.presentation.presenter.DetailsScreenModel
import com.example.feature.home.impl.presentation.presenter.HomeScreenModel
import com.example.feature.home.impl.usecase.GetFoodByIdUseCaseImpl
import com.example.feature.home.impl.usecase.GetFoodListUseCaseImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {
    single {
        provideFoodApi(
            retrofit = get()
        )
    }
    single {
        provideFoodRepository(
            foodApi = get()
        )
    }
    single {
        provideGetFoodByIdUseCase(
            foodRepository = get()
        )
    }
    single {
        provideGetFoodListUseCase(
            foodRepository = get()
        )
    }
    factory {
        HomeScreenModel(
            getFoodListUseCase = get(),
            firebaseAnalytics = get(),
        )
    }
    factory {
        DetailsScreenModel(
            getFoodByIdUseCase = get(),
            saveProductUseCase = get(),
            getProductByTitleUseCase = get(),
            updateProductUseCase = get(),
        )
    }
}

private fun provideFoodApi(
    retrofit: Retrofit
): FoodApi = retrofit.create(FoodApi::class.java)

private fun provideFoodRepository(
    foodApi: FoodApi
): FoodRepository = FoodRepositoryImpl(foodApi)

private fun provideGetFoodByIdUseCase(
    foodRepository: FoodRepository
): GetFoodByIdUseCase = GetFoodByIdUseCaseImpl(foodRepository)

private fun provideGetFoodListUseCase(
    foodRepository: FoodRepository
): GetFoodListUseCase = GetFoodListUseCaseImpl(foodRepository)
