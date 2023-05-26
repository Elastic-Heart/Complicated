package com.example.complicated.domain

import com.example.complicated.data.CondoUnitFilter
import com.example.complicated.data.avril.AvrilDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FilterAvrilSongsUseCase(
    private val avrilDataSource: AvrilDataSource,
    private val condoUnitFilter: CondoUnitFilter
) {
    operator fun invoke(text: CharSequence?) = flow {
        emit(AvrilSongsState.Loading)

        val allSongs = avrilDataSource()

        val filteredSongs = condoUnitFilter.filter(text, allSongs)

        emit(AvrilSongsState.Loaded(filteredSongs))
    }.catch { emit(AvrilSongsState.Failure) }
}