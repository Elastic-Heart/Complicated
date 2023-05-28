package com.martini.complicated.details

import org.koin.dsl.module

val detailsModule = module {

    factory { GetSongDetailsUseCase(
        songDetailDataSource = get()
    ) }

    factory { GetSongDetailsViewModel(
        getSongDetailsUseCase = get(),
        savedStateHandle = get(),
        dispatchers = get()
    ) }
}