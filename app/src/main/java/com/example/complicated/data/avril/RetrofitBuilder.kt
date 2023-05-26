package com.example.complicated.data.avril

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val baseURl = "https://646fb83009ff19b12087b0a7.mockapi.io/api/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseURl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val avrilService: AvrilService = retrofit.create(AvrilService::class.java)
}