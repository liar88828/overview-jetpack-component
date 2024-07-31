package com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "shopping")
data class Shopping(
	@ColumnInfo("shopping_id")
	@PrimaryKey(autoGenerate = true)
	val shoppingId: Int = 0,
	val shoppingName: String,
)

@Entity(tableName = "item")
data class Item(
	@ColumnInfo(name = "item_id")
	@PrimaryKey(autoGenerate = true)
	val itemId: Int = 0,
	val itemName: String,
	val qty: String,
	val shoppingIdFk: Int,
	val storeIdFk: Int,
	val date: Date,
	val isChecked: Boolean
)

@Entity(tableName = "store")
data class Store(
	@ColumnInfo(name = "store_id")
	@PrimaryKey(autoGenerate = true)
	val storeId: Int = 0,
	val listIdFk: Int,
	val storeName: String
)