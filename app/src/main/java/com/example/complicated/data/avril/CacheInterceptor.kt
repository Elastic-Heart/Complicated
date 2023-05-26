package com.example.complicated.data.avril

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val cacheControl = CacheControl.Builder()
            .maxAge(60, TimeUnit.SECONDS)
            .build()

        val request = chain.request()
            .newBuilder()
            .cacheControl(cacheControl)
            .build()

        return chain.proceed(request)
    }
}