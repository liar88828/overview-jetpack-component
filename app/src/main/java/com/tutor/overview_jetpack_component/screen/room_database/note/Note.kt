package com.tutor.overview_jetpack_component.screen.room_database.note

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
	val name: String,
	val body: String,
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0
)
