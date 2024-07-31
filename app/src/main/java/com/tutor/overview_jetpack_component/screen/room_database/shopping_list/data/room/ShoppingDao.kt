package com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Item
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Shopping
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Store
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(item: Item)

	@Update(onConflict = OnConflictStrategy.REPLACE)
	suspend fun update(item: Item)

	@Delete
	suspend fun delete(item: Item)

	@Query("SELECT * FROM item ")
	fun getAllItems(): Flow<List<Item>>

	@Query("SELECT * FROM item WHERE item_id = :id")
	fun getItem(id: Int): Flow<Item>

}

@Dao
interface StoreDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(store: Store)

	@Update(onConflict = OnConflictStrategy.REPLACE)
	suspend fun update(store: Store)

	@Delete
	suspend fun delete(store: Store)

	@Query("SELECT * FROM store ")
	fun getAllStores(): Flow<List<Store>>

	@Query("SELECT * FROM store WHERE store_id = :id")
	fun getStore(id: Int): Flow<Store>

}

@Dao
interface ShoppingDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertShop(shoppingList: Shopping)

	@Query(
		""" SELECT * FROM 
				item AS I INNER JOIN shopping AS S 
				ON I.shoppingIdFk = S.shopping_id INNER JOIN store AS ST 
				ON I.storeIdFk = ST.store_id """
	)
	fun getAllShopping(): Flow<List<ItemWithStoreAndList>>

	@Query(
		"""SELECT * FROM item as I INNER JOIN shopping AS S 
		ON I.shoppingIdFk =S.shopping_id INNER JOIN store AS ST
		ON I.storeIdFk=ST.store_id WHERE S.shopping_id = :id
		 """
	)
	fun withShoppingId(id: Int): Flow<List<ItemWithStoreAndList>>

	@Query(
		"""SELECT * FROM item as I INNER JOIN shopping AS S 
		ON I.shoppingIdFk =S.shopping_id INNER JOIN store AS ST
		ON I.storeIdFk=ST.store_id WHERE I.item_id = :id
		 """
	)
	fun withItemId(id: Int): Flow<ItemWithStoreAndList>
}

data class ItemWithStoreAndList(
	@Embedded val itemList: Item,
	@Embedded val shoppingList: Shopping,
	@Embedded val storeList: Store
)
