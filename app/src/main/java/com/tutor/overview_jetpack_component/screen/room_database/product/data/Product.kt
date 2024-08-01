package com.tutor.overview_jetpack_component.screen.room_database.product.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val name: String,
	val qty: String,
	val price: String,
	val description: String
)
