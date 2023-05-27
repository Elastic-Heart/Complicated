package com.martini.data

class SongDetailIncredibleDataSource(
    private val songDetailsService: SongDetailsService
) : SongDetailDataSource {
    override suspend fun invoke(id: Long): SongDetail {
        return songDetailsService.getSongDetail(id)
    }
}