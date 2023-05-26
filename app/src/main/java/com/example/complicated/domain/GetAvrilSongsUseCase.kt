package com.example.complicated.domain

import com.example.complicated.data.avril.AvrilDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetAvrilSongsUseCase(
    private val avrilDataSource: AvrilDataSource
) {
    operator fun invoke() = flow {
        emit(AvrilSongsState.Loading)

        val condos = avrilDataSource()

        emit(AvrilSongsState.Loaded(condos))
    }.catch {
        println("MY_ERROR_MESSAGE: ${it.message}")
        emit(AvrilSongsState.Failure)
    }
}