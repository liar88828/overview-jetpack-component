package com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.converter.DateConverter
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Item
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Shopping
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Store
import kotlin.concurrent.Volatile

@TypeConverters(value = [DateConverter::class])
@Database(
	entities = [Shopping::class, Item::class, Store::class],
	version = 1,
	exportSchema = false,
)
abstract class ShoppingDatabase : RoomDatabase() {
	abstract fun shoppingDao(): ShoppingDao
	abstract fun itemDao(): ItemDao
	abstract fun storeDao(): StoreDao

	companion object {
		@Volatile
		var INSTANCE: ShoppingDatabase? = null
		fun getDatabase(context: Context): ShoppingDatabase {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context = context,
					klass = ShoppingDatabase::class.java,
					name = "shopping_database"
				).build()
				INSTANCE = instance
				return instance
			}

		}
	}
}