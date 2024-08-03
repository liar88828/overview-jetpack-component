package com.tutor.overview_jetpack_component.MVI.notes.core.domain.model

data class NoteItem(

	var title: String,
	var description: String,
	var imageUrl: String,
	var dateAdded: String,
	var id: Int = 0

)
