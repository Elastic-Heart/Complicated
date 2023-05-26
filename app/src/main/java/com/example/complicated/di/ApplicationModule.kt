package com.example.complicated.di

import android.content.Context
import com.example.complicated.common.AppCoroutineDispatchers
import com.example.complicated.data.avril.AvrilService
import com.example.complicated.data.avril.CacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ApplicationBindModule::class])
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun providesCacheInterceptor(): CacheInterceptor {
        return CacheInterceptor()
    }

    @Provides
    fun providesOkHttpClientCache(
        @ApplicationContext context: Context
    ) : Cache {
        val size = (5 * 1024 * 1024).toLong()
        return Cache(context.cacheDir, size)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        cache: Cache,
        cacheInterceptor: CacheInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .callTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .cache(cache)
            .addInterceptor(cacheInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://646fb83009ff19b12087b0a7.mockapi.io/api/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesAvrilService(
        retrofit: Retrofit
    ): AvrilService {
        return retrofit.create(AvrilService::class.java)
    }

    @Provides
    fun providesAppCoroutineDispatchers(): AppCoroutineDispatchers {
        return AppCoroutineDispatchers()
    }
}