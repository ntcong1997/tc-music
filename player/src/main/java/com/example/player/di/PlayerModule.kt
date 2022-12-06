package com.example.player.di

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by TC on 05/12/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
class PlayerModule {
    @Singleton
    @Provides
    fun providePlayer(@ApplicationContext context: Context): ExoPlayer = ExoPlayer.Builder(context).build()
}
