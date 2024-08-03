package com.tutor.overview_jetpack_component.MVI.notes.core.data.mapper

import com.tutor.overview_jetpack_component.MVI.notes.core.data.local.NoteEntity
import com.tutor.overview_jetpack_component.MVI.notes.core.domain.model.NoteItem

fun NoteItem.toNoteEntityForInsert(): NoteEntity {
	return NoteEntity(
		title = title,
		desctiption = description,
		imageUrl = imageUrl,
		dateAdded = dateAdded,
	)
}

fun NoteItem.toNoteEntityForDelete(): NoteEntity {
	return NoteEntity(
		title = title,
		desctiption = description,
		imageUrl = imageUrl,
		dateAdded = dateAdded,
		id = id

	)
}

fun NoteItem.toNoteItem(): NoteEntity {
	return NoteEntity(
		title = title,
		desctiption = description,
		imageUrl = imageUrl,
		dateAdded = dateAdded,
		id = id ?: 0

	)
}