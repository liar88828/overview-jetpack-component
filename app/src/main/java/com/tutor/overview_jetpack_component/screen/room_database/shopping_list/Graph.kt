package com.tutor.overview_jetpack_component.screen.room_database.shopping_list

import android.content.Context
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.ShoppingDatabase
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.repository.Repository

object Graph {
	lateinit var db: ShoppingDatabase
		private set
	val repository by lazy {
		Repository(
			shoppingDao = db.shoppingDao(),
			storeDao = db.storeDao(),
			itemDao = db.itemDao()
		)
	}

	fun provide(context: Context) {
		db = ShoppingDatabase.getDatabase(context)
	}
}