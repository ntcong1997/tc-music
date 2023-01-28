package com.example.player.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.media.app.NotificationCompat.MediaStyle
import androidx.media.session.MediaButtonReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by TC on 13/12/2022.
 */

@Singleton
class MediaNotificationManager @Inject constructor(
    private val mediaNotificationConfig: MediaNotificationConfig,
    @ApplicationContext private val context: Context
) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val playAction = NotificationCompat.Action(
        mediaNotificationConfig.playDrawable,
        null,
        MediaButtonReceiver.buildMediaButtonPendingIntent(
            context,
            PlaybackStateCompat.ACTION_PLAY
        )
    )

    private val pauseAction = NotificationCompat.Action(
        mediaNotificationConfig.pauseDrawable,
        null,
        MediaButtonReceiver.buildMediaButtonPendingIntent(
            context,
            PlaybackStateCompat.ACTION_PAUSE
        )
    )

    private val previousAction = NotificationCompat.Action(
        mediaNotificationConfig.previousDrawable,
        null,
        MediaButtonReceiver.buildMediaButtonPendingIntent(
            context,
            PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
        )
    )

    private val nextAction = NotificationCompat.Action(
        mediaNotificationConfig.nextDrawable,
        null,
        MediaButtonReceiver.buildMediaButtonPendingIntent(
            context,
            PlaybackStateCompat.ACTION_SKIP_TO_NEXT
        )
    )

    fun createNotification(
        mediaMetadata: MediaMetadataCompat,
        currentTrackVersion: String?,
        playbackState: PlaybackStateCompat,
        token: MediaSessionCompat.Token
    ) {
        val isPlaying = playbackState.state == PlaybackStateCompat.STATE_PLAYING
        val description = mediaMetadata.description

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        val intent = Intent(context, mediaNotificationConfig.activityToOpenOnClick).apply {
            val bundle = Bundle().apply {
                putString(EXTRA_DATA_TRACK_ID, description.mediaId)
                putString(EXTRA_DATA_TRACK_VERSION, currentTrackVersion)
            }
            putExtras(bundle)
        }
        val flagPendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_UPDATE_CURRENT
        val pendingIntent = PendingIntent.getActivity(context, MEDIA_NOTIFICATION_ID, intent, flagPendingIntent)

        val builder = NotificationCompat.Builder(context, MEDIA_NOTIFICATION_CHANNEL_ID)
        builder.setStyle(
            MediaStyle()
                .setMediaSession(token)
                .setShowActionsInCompactView(0, 1, 2)
                .setShowCancelButton(true)
                .setCancelButtonIntent(
                    MediaButtonReceiver.buildMediaButtonPendingIntent(
                        context,
                        PlaybackStateCompat.ACTION_STOP
                    )
                )
        )
            .setColor(ContextCompat.getColor(context, mediaNotificationConfig.colorResource))
            .setSmallIcon(mediaNotificationConfig.smallIconDrawable)
            .setContentIntent(pendingIntent)
            .setContentTitle(description.title)
            .setContentText(description.subtitle)
            .setLargeIcon(description.iconBitmap)
            .setDeleteIntent(
                MediaButtonReceiver.buildMediaButtonPendingIntent(
                    context, PlaybackStateCompat.ACTION_PAUSE
                )
            )
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .priority = NotificationCompat.PRIORITY_MAX

        if (playbackState.actions and PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS != 0L) {
            builder.addAction(previousAction)
        }

        builder.addAction(if (isPlaying) pauseAction else playAction)

        if (playbackState.actions and PlaybackStateCompat.ACTION_SKIP_TO_NEXT != 0L) {
            builder.addAction(nextAction)
        }

        with(notificationManager) {
            notify(MEDIA_NOTIFICATION_ID, builder.build())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        if (notificationManager.getNotificationChannel(MEDIA_NOTIFICATION_CHANNEL_ID) == null) {
            val name = "TCMusic"
            val description = "Channel for playing music"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(MEDIA_NOTIFICATION_CHANNEL_ID, name, importance)
            channel.description = description
            channel.setShowBadge(false)
            // this is a workaround for a bug in oreo where notifications keep vibrating
            channel.vibrationPattern = longArrayOf(0L)
            channel.setSound(null, null)
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun cancelNotification() {
        with(notificationManager) {
            cancel(MEDIA_NOTIFICATION_ID)
        }
    }

    companion object {
        private const val MEDIA_NOTIFICATION_CHANNEL_ID = "MEDIA_NOTIFICATION_CHANNEL_ID"
        private const val MEDIA_NOTIFICATION_ID = 10001

        const val EXTRA_DATA_TRACK_ID = "EXTRA_DATA_TRACK_ID"
        const val EXTRA_DATA_TRACK_VERSION = "EXTRA_DATA_TRACK_VERSION"
    }
}

data class MediaNotificationConfig(
    val playDrawable: Int,
    val pauseDrawable: Int,
    val previousDrawable: Int,
    val nextDrawable: Int,
    val smallIconDrawable: Int,
    val colorResource: Int,
    val activityToOpenOnClick: Class<*>
)
