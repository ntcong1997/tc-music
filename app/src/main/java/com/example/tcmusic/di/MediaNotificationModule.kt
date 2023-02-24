package com.example.tcmusic.di

import com.example.tcmusic.core.player.service.MediaNotificationConfig
import com.example.tcmusic.ui.MainActivity
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
    // Provide notification config for player module
    // Why we must provide? Because we need to pass activity on click notification.
    @Singleton
    @Provides
    fun provideMediaNotificationConfig() = MediaNotificationConfig(
        MainActivity::class.java
    )
}
