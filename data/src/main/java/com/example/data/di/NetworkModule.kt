package com.example.data.di

import android.content.Context
import com.example.data.BuildConfig
import com.example.data.datasource.*
import com.example.data.remote.SHAZAM_DOMAIN_API
import com.example.data.remote.apiservice.ShazamApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
                .addHeader("X-RapidAPI-Key", BuildConfig.X_RapidAPI_Key)
                .addHeader("X-RapidAPI-Host", BuildConfig.X_RapidAPI_Host)
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
    fun provideShazamApiService(gson: Gson, okHttpClient: OkHttpClient): ShazamApiService =
        Retrofit.Builder()
            .baseUrl(SHAZAM_DOMAIN_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ShazamApiService::class.java)

    @Singleton
    @Provides
    fun provideChartDataSource(
        shazamApiService: ShazamApiService
    ): ChartDataSource = ChartDataSourceImpl(shazamApiService)

    @Singleton
    @Provides
    fun provideTrackDataSource(
        shazamApiService: ShazamApiService,
        gson: Gson
    ): TrackDataSource = TrackDataSourceImpl(shazamApiService, gson)

    @Singleton
    @Provides
    fun provideArtistDataSource(
        shazamApiService: ShazamApiService
    ): ArtistDataSource = ArtistDataSourceImpl(shazamApiService)
}
