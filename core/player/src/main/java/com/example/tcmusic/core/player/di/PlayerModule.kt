package com.example.tcmusic.core.player.di

import android.content.Context
import com.example.tcmusic.core.player.Player
import com.example.tcmusic.core.player.PlayerManager
import com.example.tcmusic.core.player.data.PlayerData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by TC on 23/02/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
class PlayerModule {
    @Provides
    @Singleton
    fun providePlayer(
        playerData: PlayerData,
        @ApplicationContext context: Context
    ): Player = PlayerManager(playerData, context)
}
