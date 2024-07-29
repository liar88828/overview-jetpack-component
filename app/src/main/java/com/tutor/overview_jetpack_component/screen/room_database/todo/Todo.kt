package com.tutor.overview_jetpack_component.screen.room_database.todo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Todo(
	@PrimaryKey(autoGenerate = true)
	var id: Int = 0,
	var title: String,
	var createAt: Date
)