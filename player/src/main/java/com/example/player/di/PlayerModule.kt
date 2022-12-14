package com.example.player.di

import android.content.Context
import com.example.domain.di.ApplicationScope
import com.example.domain.player.Player
import com.example.player.PlayerManager
import com.example.player.data.PlayerData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

/**
 * Created by TC on 14/12/2022.
 */

@InstallIn(SingletonComponent::class)
@Module
class PlayerModule {
    @Singleton
    @Provides
    fun providePlayerManager(
        playerData: PlayerData,
        @ApplicationContext context: Context
    ): Player = PlayerManager(
        playerData,
        context
    )
}