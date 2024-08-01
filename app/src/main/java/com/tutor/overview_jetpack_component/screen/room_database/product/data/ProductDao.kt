package com.tutor.overview_jetpack_component.screen.room_database.product.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
	@Insert
	suspend fun create(product: Product)

	@Update
	suspend fun update(product: Product)

	@Delete
	suspend fun delete(product: Product)

	@Query("SELECT * FROM product ORDER BY name ASC")
	fun getAllName(): Flow<List<Product>>

	@Query("SELECT * FROM product ORDER BY price ASC")
	fun getAllPrice(): Flow<List<Product>>

	@Query("SELECT * FROM product WHERE id =:id ")
	fun getById(id: Int): Flow<Product>

}