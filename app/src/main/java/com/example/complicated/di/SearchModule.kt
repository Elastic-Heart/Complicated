package com.example.complicated.di

import com.example.complicated.data.CondoNameFilter
import com.example.complicated.data.CondoUnitFilter
import com.example.complicated.data.avril.AvrilDataSource
import com.example.complicated.data.avril.AvrilLavigneIncredibleDataSource
import com.example.complicated.domain.FilterAvrilSongsUseCase
import com.example.complicated.domain.GetAvrilSongsUseCase
import com.example.complicated.ui.SearchViewModel
import org.koin.dsl.module

val searchModule = module {
    factory<AvrilDataSource> { AvrilLavigneIncredibleDataSource(get()) }

    factory { GetAvrilSongsUseCase(get()) }

    factory { FilterAvrilSongsUseCase(get(), get()) }

    factory<CondoUnitFilter> { CondoNameFilter() }

    factory { SearchViewModel(
        getAvrilSongsUseCase = get(),
        filterAvrilSongsUseCase = get(),
        dispatcher = get()
    ) }
}