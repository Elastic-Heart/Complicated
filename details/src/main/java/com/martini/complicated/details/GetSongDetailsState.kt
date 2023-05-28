package com.martini.complicated.details


sealed interface GetSongDetailsState {
    object Failure: GetSongDetailsState

    object Loading: GetSongDetailsState

    class Loaded(
        val songDetail: SongDetails
    ) : GetSongDetailsState
}
