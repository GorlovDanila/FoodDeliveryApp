package com.example.core.db.di

import android.content.Context
import androidx.room.Room
import com.example.core.db.AppDatabase
import com.example.core.db.dao.ProductDao
import org.koin.dsl.module

private const val DATABASE_NAME = "product_db"

val databaseModule = module {
    single {
        provideDatabase(
            context = get()
        )
    }
    single {
        provideProductDao(
            database = get()
        )
    }
}

private fun provideDatabase(
    context: Context,
): AppDatabase =
    Room
        .databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        )
        .build()

private fun provideProductDao(
    database: AppDatabase
) : ProductDao = database.getProductDao()
