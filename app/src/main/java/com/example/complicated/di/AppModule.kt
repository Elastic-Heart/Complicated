package com.example.complicated.di

import com.example.complicated.data.avril.AvrilService
import com.example.complicated.data.avril.CacheInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single {  CacheInterceptor() }

    factory {
        val size = (5*1024*1024).toLong()
        Cache(androidContext().cacheDir, size)
    }

    single {
        OkHttpClient.Builder()
            .callTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .callTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .cache(get())
            .addInterceptor(get<CacheInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://646fb83009ff19b12087b0a7.mockapi.io/api/v1/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create(AvrilService::class.java)
    }
}