package com.martini.complicated.details


sealed class GetSongDetailsState {
    object Failure: GetSongDetailsState()

    object Loading: GetSongDetailsState()

    class Loaded(
        val songDetail: SongDetails
    ) : GetSongDetailsState()
}
