package com.tutor.overview_jetpack_component.screen.room_database.market.data

import android.content.Context

object UserGraph {
	lateinit var db: UserDatabase
		private set
	val dao: UserDao by lazy { db.dao }

	fun provide(context: Context) {
		db = UserDatabase.getDatabase(context)
	}
}