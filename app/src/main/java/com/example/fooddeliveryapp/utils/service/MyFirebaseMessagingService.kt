package com.example.fooddeliveryapp.utils.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.ui.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

private const val TAG = "MyFirebaseMsgService"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Timber.tag(TAG).d("From: %s", remoteMessage.from)

        if (remoteMessage.data.isNotEmpty()) {
            Timber.tag(TAG).d("Message data payload: %s", remoteMessage.data)
        }

        remoteMessage.notification?.let {
            Timber.tag(TAG).d("Message Notification Body: %s", it.body)
            it.body?.let { body -> sendNotification(body) }
        }
    }

    override fun onNewToken(token: String) {
        Timber.tag(TAG).d("Refreshed token: %s", token)

        sendRegistrationToServer(token)
    }

    @SuppressLint("TimberArgCount")
    private fun sendRegistrationToServer(token: String?) {
        Timber.tag(TAG).d("%s)", "sendRegistrationTokenToServer(%s", token)
    }

    private fun sendNotification(messageBody: String) {
        val requestCode = 0
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this,
            requestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE,
        )

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_stat_ic_notification)
            .setContentTitle(getString(R.string.fcm_message))
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            "Channel human readable title",
            NotificationManager.IMPORTANCE_DEFAULT,
        )
        notificationManager.createNotificationChannel(channel)

        val notificationId = 0
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}
