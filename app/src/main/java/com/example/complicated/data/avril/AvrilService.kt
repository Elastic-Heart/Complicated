package com.example.complicated.data.avril

import com.example.complicated.data.CondoUnit
import retrofit2.http.GET

interface AvrilService {
    @GET("songs")
    suspend fun getSongs() : List<CondoUnit>
}