package com.example.tcmusic.di

import com.example.player.service.MediaNotificationConfig
import com.example.tcmusic.R
import com.example.tcmusic.ui.main.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by TC on 13/12/2022.
 */

@InstallIn(SingletonComponent::class)
@Module
class MediaNotificationModule {
    @Singleton
    @Provides
    fun provideMediaNotificationConfig() = MediaNotificationConfig(
        R.drawable.ic_play_2,
        R.drawable.ic_pause_2,
        R.drawable.ic_next,
        R.drawable.ic_previous,
        R.drawable.ic_app,
        R.color.black,
        MainActivity::class.java
    )
}