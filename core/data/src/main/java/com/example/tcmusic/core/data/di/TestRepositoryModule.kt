package com.example.tcmusic.core.data.di

import com.example.tcmusic.core.data.repository.ArtistRepository
import com.example.tcmusic.core.data.repository.fake.FakeArtistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

/**
 * Created by TC on 23/02/2023.
 */

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class TestRepositoryModule {
//    @Binds
//    abstract fun bindChartRepository(chartRepositoryImpl: ChartRepositoryImpl): ChartRepository
//
//    @Binds
//    abstract fun bindTrackRepository(trackRepositoryImpl: TrackRepositoryImpl): TrackRepository

    @Binds
    abstract fun bindArtistRepository(fakeArtistRepository: FakeArtistRepository): ArtistRepository
}
