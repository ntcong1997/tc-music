package com.example.tcmusic.core.network.di

import com.example.tcmusic.core.network.datasource.ArtistDataSource
import com.example.tcmusic.core.network.datasource.ChartDataSource
import com.example.tcmusic.core.network.datasource.TrackDataSource
import com.example.tcmusic.core.network.datasource.fake.FakeArtistDataSource
import com.example.tcmusic.core.network.datasource.fake.FakeChartDataSource
import com.example.tcmusic.core.network.datasource.fake.FakeTrackDataSource
import com.example.tcmusic.core.network.retrofit.fake.FakeRetrofitShazamNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/**
 * Created by TC on 23/02/2023.
 */

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class TestNetworkModule {
    @Singleton
    @Provides
    fun provideChartDataSource(
        fakeRetrofitShazamNetwork: FakeRetrofitShazamNetwork
    ): ChartDataSource = FakeChartDataSource(fakeRetrofitShazamNetwork)

    @Singleton
    @Provides
    fun provideTrackDataSource(
        fakeRetrofitShazamNetwork: FakeRetrofitShazamNetwork
    ): TrackDataSource = FakeTrackDataSource(fakeRetrofitShazamNetwork)

    @Singleton
    @Provides
    fun provideArtistDataSource(
        fakeRetrofitShazamNetwork: FakeRetrofitShazamNetwork
    ): ArtistDataSource = FakeArtistDataSource(fakeRetrofitShazamNetwork)
}
