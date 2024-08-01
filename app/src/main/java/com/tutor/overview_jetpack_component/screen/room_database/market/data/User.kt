package com.tutor.overview_jetpack_component.screen.room_database.market.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
	val name: String,
	val age: String,
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0
)