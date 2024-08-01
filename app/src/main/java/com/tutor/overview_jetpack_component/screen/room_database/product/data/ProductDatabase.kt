package com.tutor.overview_jetpack_component.screen.room_database.product.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.concurrent.Volatile

@Database(
	entities = [Product::class],
	version = 1,
)
abstract class ProductDatabase : RoomDatabase() {
	abstract val dao: ProductDao

	companion object {
		@Volatile
		var INSTANCE: ProductDatabase? = null
		val DATABASE_NAME = "product.db"
		fun getDatabase(context: Context): ProductDatabase {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context,
					ProductDatabase::class.java,
					DATABASE_NAME
				).build()
				INSTANCE = instance
				return instance
			}

		}

	}

}