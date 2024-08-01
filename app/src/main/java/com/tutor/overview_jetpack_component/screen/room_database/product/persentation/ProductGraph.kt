package com.tutor.overview_jetpack_component.screen.room_database.product.persentation

import android.content.Context
import com.tutor.overview_jetpack_component.screen.room_database.product.data.ProductDao
import com.tutor.overview_jetpack_component.screen.room_database.product.data.ProductDatabase

object ProductGraph {
	lateinit var db: ProductDatabase
		private set
	val dao: ProductDao by lazy { db.dao }

	fun provide(context: Context) {
		db = ProductDatabase.getDatabase(context)
	}

}