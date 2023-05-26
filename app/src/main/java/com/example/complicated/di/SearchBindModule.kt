package com.example.complicated.di

import com.example.complicated.data.CondoNameFilter
import com.example.complicated.data.CondoUnitFilter
import com.example.complicated.data.avril.AvrilDataSource
import com.example.complicated.data.avril.AvrilLavigneIncredibleDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchBindModule {

    @Binds
    abstract fun bindCondoUnitFilter(
        condoNameFilter: CondoNameFilter
    ) : CondoUnitFilter

    @Binds
    abstract fun bindAvrilDataSource(
        avrilLavigneIncredibleDataSource: AvrilLavigneIncredibleDataSource
    ) : AvrilDataSource
}