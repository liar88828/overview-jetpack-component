package com.tutor.overview_jetpack_component

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.room.Room
import com.tutor.overview_jetpack_component.screen.notification_screen.NOTIFICATION_CHANNEL_ID
import com.tutor.overview_jetpack_component.screen.room_database.market.data.UserGraph
import com.tutor.overview_jetpack_component.screen.room_database.product.persentation.ProductGraph
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.Graph
import com.tutor.overview_jetpack_component.screen.room_database.todo.TodoDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
	companion object {
		lateinit var todoDatabase: TodoDatabase
//		lateinit var userDatabase: UserDatabase

	}

	override fun onCreate() {
		super.onCreate()
		Graph.provide(this)
		UserGraph.provide(this)
		ProductGraph.provide(this)
// user Database are not contractor
//		userDatabase = Room.databaseBuilder(
//			applicationContext,
//			UserDatabase::class.java,
//			UserDatabase.DATABASE_NAME
//		).build()
// Todo Database
		todoDatabase = Room.databaseBuilder(
			applicationContext,
			TodoDatabase::class.java,
			TodoDatabase.DATABASE_NAME
		).build()
//
		val notificationManager =
			applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
		val notificationChannel = NotificationChannel(
			NOTIFICATION_CHANNEL_ID,
			NOTIFICATION_CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH
		)
		notificationManager.createNotificationChannel(notificationChannel)
	}
}