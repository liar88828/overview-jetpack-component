package com.tutor.overview_jetpack_component.screen.room_database.market.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
	entities = [User::class],
	version = 1
)
abstract class UserDatabase : RoomDatabase() {
	abstract val dao: UserDao

	companion object {
		const val DATABASE_NAME = "user.db"

		@Volatile
		var INSTANCE: UserDatabase? = null
		fun getDatabase(context: Context): UserDatabase {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context = context,
					klass = UserDatabase::class.java,
					name = DATABASE_NAME
				).build()
				INSTANCE = instance
				return instance
			}
		}
	}
}