package com.martini.data

interface SongDetailDataSource {
    suspend operator fun invoke(id: Long): SongDetail
}