package com.example.core.network.di


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_ENDPOINT = "http://89.191.244.157:8080/"

val networkModule = module {
    single { GsonConverterFactory.create() }
    single(qualifier = named("base_url")) { API_ENDPOINT }
    single { provideLoggingInterceptor() }
    single {
        provideHttpClient(get())
    }
    single {
        provideRetrofit(
            httpClient = get(),
            gsonFactory = get(),
            baseUrl = get(named("base_url"))
        )
    }
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private fun provideHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(10L, TimeUnit.SECONDS)
        .build()

private fun provideRetrofit(
    httpClient: OkHttpClient,
    gsonFactory: GsonConverterFactory,
    baseUrl: String,
): Retrofit = Retrofit.Builder()
    .client(httpClient)
    .baseUrl(baseUrl)
    .addConverterFactory(gsonFactory)
    .build()