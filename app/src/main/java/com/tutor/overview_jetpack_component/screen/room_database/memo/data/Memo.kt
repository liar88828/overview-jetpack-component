package com.tutor.overview_jetpack_component.screen.room_database.memo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memo(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val title: String,
	val description: String,
//
	val dateAdded: Long,
)