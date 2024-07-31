package com.tutor.overview_jetpack_component.screen.room_database.shopping_list.repository

import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.ItemDao
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.ShoppingDao
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.StoreDao
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Item
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Shopping
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Store

class Repository(
	private val shoppingDao: ShoppingDao,
	private val storeDao: StoreDao,
	private val itemDao: ItemDao
) {
	val store = storeDao.getAllStores()
	val getItemsWithListAndStore = shoppingDao.getAllShopping()
	fun getShoppingId(id: Int) = shoppingDao.withShoppingId(id)
	fun getItemId(id: Int) = shoppingDao.withItemId(id)

	suspend fun insertStore(store: Store) {
		storeDao.insert(store)
	}

	suspend fun insertShop(shop: Shopping) {
		shoppingDao.insertShop(shop)
	}

	suspend fun insertItem(item: Item) {
		itemDao.insert(item)
	}

	suspend fun deleteItem(item: Item) {
		itemDao.delete(item)
	}

	suspend fun update(item: Item) {
		itemDao.update(item)

	}
}