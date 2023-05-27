package com.martini.data

import retrofit2.http.GET
import retrofit2.http.Path

interface SongDetailsService {
    @GET("songs/{id}")
    suspend fun getSongDetail(@Path("id") id: Long) : SongDetail
}