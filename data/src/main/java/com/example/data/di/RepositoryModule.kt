package com.example.data.di

import com.example.data.repositoryimpl.ChartRepositoryImpl
import com.example.data.repositoryimpl.TrackRepositoryImpl
import com.example.domain.repository.ChartRepository
import com.example.domain.repository.TrackRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by TC on 17/11/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindChartRepository(chartRepositoryImpl: ChartRepositoryImpl): ChartRepository

    @Singleton
    @Binds
    abstract fun bindTrackRepository(trackRepositoryImpl: TrackRepositoryImpl): TrackRepository
}