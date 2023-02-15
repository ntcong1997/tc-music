package com.example.tcmusic.core.data.di

import com.example.tcmusic.core.data.repository.*
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

    @Singleton
    @Binds
    abstract fun bindArtistRepository(artistRepositoryImpl: ArtistRepositoryImpl): ArtistRepository
}
