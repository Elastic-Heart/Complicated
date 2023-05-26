package com.example.complicated.domain

import com.example.complicated.data.CondoUnit

sealed class AvrilSongsState {
    class Loaded(
        val condos: List<CondoUnit>
    ) : AvrilSongsState()
    object Loading: AvrilSongsState()
    object Failure: AvrilSongsState()
}
