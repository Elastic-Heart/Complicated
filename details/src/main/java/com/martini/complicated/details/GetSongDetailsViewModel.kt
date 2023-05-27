package com.martini.complicated.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class GetSongDetailsViewModel(
    private val getSongDetailsUseCase: GetSongDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<GetSongDetailsState>(GetSongDetailsState.Loading)

    val state: State<GetSongDetailsState> = _state

    init {
        val id: Long? = savedStateHandle[DetailsActivity.songId]
        id?.let { songId ->
            getSongDetailsUseCase(songId)
                .flowOn(Dispatchers.IO)
                .onEach { _state.value = it }
                .launchIn(viewModelScope)
        } ?: run {
            _state.value = GetSongDetailsState.Failure
        }
    }
}