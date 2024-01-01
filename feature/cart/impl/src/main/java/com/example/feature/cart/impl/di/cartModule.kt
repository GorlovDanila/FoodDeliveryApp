package com.example.feature.cart.impl.di

import com.example.core.db.dao.ProductDao
import com.example.feature.cart.api.repository.ProductRepository
import com.example.feature.cart.api.usecase.DeleteAllProductsUseCase
import com.example.feature.cart.api.usecase.DeleteProductUseCase
import com.example.feature.cart.api.usecase.GetAllProductsUseCase
import com.example.feature.cart.api.usecase.GetProductByIdUseCase
import com.example.feature.cart.api.usecase.GetProductByTitleUseCase
import com.example.feature.cart.api.usecase.GetSummaryPriceUseCase
import com.example.feature.cart.api.usecase.SaveProductUseCase
import com.example.feature.cart.api.usecase.UpdateProductUseCase
import com.example.feature.cart.impl.data.ProductRepositoryImpl
import com.example.feature.cart.impl.presentation.presenter.ShoppingCartScreenModel
import com.example.feature.cart.impl.usecase.DeleteAllProductsUseCaseImpl
import com.example.feature.cart.impl.usecase.DeleteProductUseCaseImpl
import com.example.feature.cart.impl.usecase.GetAllProductsUseCaseImpl
import com.example.feature.cart.impl.usecase.GetProductByIdUseCaseImpl
import com.example.feature.cart.impl.usecase.GetProductByTitleUseCaseImpl
import com.example.feature.cart.impl.usecase.GetSummaryPriceUseCaseImpl
import com.example.feature.cart.impl.usecase.SaveProductUseCaseImpl
import com.example.feature.cart.impl.usecase.UpdateProductUseCaseImpl
import org.koin.dsl.module

val cartModule = module {
    single {
        provideProductRepository(
            productDao = get()
        )
    }
    single {
        provideDeleteProductUseCase(
            productRepository = get()
        )
    }
    single {
        provideDeleteAllProductsUseCase(
            productRepository = get()
        )
    }
    single {
        provideGetAllProductsUseCase(
            productRepository = get()
        )
    }
    single {
        provideGetProductByIdUseCase(
            productRepository = get()
        )
    }
    single {
        provideGetProductByTitleUseCase(
            productRepository = get()
        )
    }
    single {
        provideSaveProductUseCase(
            productRepository = get()
        )
    }
    single {
        provideUpdateProductUseCase(
            productRepository = get()
        )
    }
    single {
        provideSummaryPriceUseCase(
            productRepository = get()
        )
    }
    factory {
        ShoppingCartScreenModel(
            getAllProductsUseCase = get(),
            deleteProductUseCase = get(),
            deleteAllProductsUseCase = get(),
            updateProductUseCase = get(),
            getSummaryPriceUseCase = get(),
        )
    }
}

private fun provideProductRepository(
    productDao: ProductDao
): ProductRepository = ProductRepositoryImpl(productDao)

private fun provideDeleteProductUseCase(
    productRepository: ProductRepository
): DeleteProductUseCase = DeleteProductUseCaseImpl(productRepository)

private fun provideDeleteAllProductsUseCase(
    productRepository: ProductRepository
): DeleteAllProductsUseCase = DeleteAllProductsUseCaseImpl(productRepository)

private fun provideGetAllProductsUseCase(
    productRepository: ProductRepository
): GetAllProductsUseCase = GetAllProductsUseCaseImpl(productRepository)

private fun provideGetProductByIdUseCase(
    productRepository: ProductRepository
): GetProductByIdUseCase = GetProductByIdUseCaseImpl(productRepository)

private fun provideGetProductByTitleUseCase(
    productRepository: ProductRepository
): GetProductByTitleUseCase = GetProductByTitleUseCaseImpl(productRepository)

private fun provideSaveProductUseCase(
    productRepository: ProductRepository
): SaveProductUseCase = SaveProductUseCaseImpl(productRepository)

private fun provideUpdateProductUseCase(
    productRepository: ProductRepository
): UpdateProductUseCase = UpdateProductUseCaseImpl(productRepository)

private fun provideSummaryPriceUseCase(
    productRepository: ProductRepository
): GetSummaryPriceUseCase = GetSummaryPriceUseCaseImpl(productRepository)
