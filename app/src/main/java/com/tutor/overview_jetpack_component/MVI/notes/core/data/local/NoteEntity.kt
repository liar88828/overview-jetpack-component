package com.tutor.overview_jetpack_component.MVI.notes.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
	@PrimaryKey(autoGenerate = true)
	var id: Int = 0,
	var title: String = "",
	var desctiption: String = "",
	var imageUrl: String = "",
	var dateAdded: String = "",
)