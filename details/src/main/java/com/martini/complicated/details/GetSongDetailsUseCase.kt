package com.martini.complicated.details

import com.martini.data.SongDetailDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetSongDetailsUseCase(
    private val songDetailDataSource: SongDetailDataSource
) {
    operator fun invoke(id: Long) = flow {
        emit(GetSongDetailsState.Loading)

        val details = songDetailDataSource(id)

        emit(
            GetSongDetailsState.Loaded(
                SongDetails(
                    id = details.id,
                    name = details.name,
                    description = details.description,
                    imageURL = details.imageURL
                )
            )
        )
    }.catch { emit(GetSongDetailsState.Failure) }
}