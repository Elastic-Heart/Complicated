package com.example.complicated.di

import com.example.complicated.data.CondoNameFilter
import com.example.complicated.data.CondoUnitFilter
import com.example.complicated.data.avril.AvrilDataSource
import com.example.complicated.data.avril.AvrilLavigneIncredibleDataSource
import com.example.complicated.data.avril.AvrilService
import com.example.complicated.domain.FilterAvrilSongsUseCase
import com.example.complicated.domain.GetAvrilSongsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module(includes = [SearchBindModule::class])
@InstallIn(ViewModelComponent::class)
object SearchModule {

    @Provides
    fun providesAvrilLavigneIncredibleDataSource(
        avrilService: AvrilService
    ): AvrilLavigneIncredibleDataSource {
        return AvrilLavigneIncredibleDataSource(avrilService)
    }

    @Provides
    fun providesGetAvrilSongsUseCase(
        avrilDataSource: AvrilDataSource
    ): GetAvrilSongsUseCase {
        return GetAvrilSongsUseCase(avrilDataSource)
    }

    @Provides
    fun providesFilterSongsUseCase(
        avrilDataSource: AvrilDataSource,
        condoUnitFilter: CondoUnitFilter
    ): FilterAvrilSongsUseCase {
        return FilterAvrilSongsUseCase(
            avrilDataSource = avrilDataSource,
            condoUnitFilter = condoUnitFilter
        )
    }

    @Provides
    fun providesCondoNameFilter(): CondoNameFilter {
        return CondoNameFilter()
    }
}