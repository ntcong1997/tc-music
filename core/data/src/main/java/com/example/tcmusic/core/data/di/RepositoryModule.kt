package com.example.tcmusic.core.data.di

import com.example.tcmusic.core.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by TC on 17/11/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindChartRepository(chartRepositoryImpl: ChartRepositoryImpl): ChartRepository

    @Binds
    abstract fun bindTrackRepository(trackRepositoryImpl: TrackRepositoryImpl): TrackRepository

    @Binds
    abstract fun bindArtistRepository(artistRepositoryImpl: ArtistRepositoryImpl): ArtistRepository
}
