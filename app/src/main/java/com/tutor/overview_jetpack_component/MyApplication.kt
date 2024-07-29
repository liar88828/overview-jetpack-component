package com.tutor.overview_jetpack_component

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.tutor.overview_jetpack_component.screen.notification_screen.NOTIFICATION_CHANNEL_ID

class MyApplication : Application() {
	override fun onCreate() {
		super.onCreate()
		val notificationManager =
			applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
		val notificationChannel = NotificationChannel(
			NOTIFICATION_CHANNEL_ID,
			NOTIFICATION_CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH
		)
		notificationManager.createNotificationChannel(notificationChannel)
	}
}