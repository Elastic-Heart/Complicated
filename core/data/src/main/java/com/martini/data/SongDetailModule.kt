package com.martini.data

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val songDetailModule = module {

    single {
        OkHttpClient.Builder()
            .callTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
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
        retrofit.create(SongDetailsService::class.java)
    }

    factory<SongDetailDataSource> { SongDetailIncredibleDataSource(
        songDetailsService = get()
    ) }
}