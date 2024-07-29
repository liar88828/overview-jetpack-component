package com.tutor.overview_jetpack_component.screen.notification_screen

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.tutor.overview_jetpack_component.MainActivity
import com.tutor.overview_jetpack_component.R

const val NOTIFICATION_CHANNEL_ID = "ch-1"
const val NOTIFICATION_CHANNEL_NAME = "Basic Notification"
const val NOTIFICATION_ID = 100
const val REQUEST_CODE = 200

class NotificationService(
	private val context: Context
) {
	private val notificationManager =
		context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
	private val myIntent = Intent(context, MainActivity::class.java)
	private val pendingIntent = PendingIntent.getActivity(
		context,
		REQUEST_CODE,
		myIntent,
		PendingIntent.FLAG_IMMUTABLE,
	)

	fun showNotification() {
		val notification =
			NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
				.setSmallIcon(R.drawable.ic_launcher_background)
				.setContentTitle("Test")
				.setContentText("Hello")
				.setPriority(NotificationCompat.PRIORITY_HIGH)
				.setContentIntent(pendingIntent)
				.build()
		notificationManager.notify(NOTIFICATION_ID, notification)
	}

}