package com.example.tcmusic.core.network.di

import android.content.Context
import com.example.tcmusic.core.network.BuildConfig
import com.example.tcmusic.core.network.datasource.*
import com.example.tcmusic.core.network.datasource.*
import com.example.tcmusic.core.network.retrofit.RetrofitShazamNetwork
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by TC on 13/10/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpCache(@ApplicationContext context: Context): Cache = Cache(context.cacheDir, 10 * 1024 * 1024) // cache size = 10MB

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(cache: Cache, logging: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder().cache(cache)

        builder.addInterceptor {
            var request = it.request()
            request = request
                .newBuilder()
                .addHeader("X-RapidAPI-Key", BuildConfig.SHAZAM_API_KEY)
                .addHeader("X-RapidAPI-Host", BuildConfig.SHAZAM_DOMAIN)
                .build()
            it.proceed(request)
        }

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(logging)
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitShazamNetwork(gson: Gson, okHttpClient: OkHttpClient): RetrofitShazamNetwork =
        Retrofit.Builder()
            .baseUrl("https//${BuildConfig.SHAZAM_DOMAIN}")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(RetrofitShazamNetwork::class.java)

    @Singleton
    @Provides
    fun provideChartDataSource(
        retrofitShazamNetwork: RetrofitShazamNetwork
    ): ChartDataSource = ChartDataSourceImpl(retrofitShazamNetwork)

    @Singleton
    @Provides
    fun provideTrackDataSource(
        retrofitShazamNetwork: RetrofitShazamNetwork
    ): TrackDataSource = TrackDataSourceImpl(retrofitShazamNetwork)

    @Singleton
    @Provides
    fun provideArtistDataSource(
        retrofitShazamNetwork: RetrofitShazamNetwork
    ): ArtistDataSource = ArtistDataSourceImpl(retrofitShazamNetwork)
}
