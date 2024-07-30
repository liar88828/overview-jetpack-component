package com.tutor.overview_jetpack_component.screen.room_database.note

import kotlinx.coroutines.flow.Flow

class NoteRepo(
	private val db: NoteDatabase
) : NoteDao {
	override suspend fun upsertNote(note: Note) {
		db.dao.upsertNote(note)
	}

	override suspend fun deleteNote(note: Note) {
		db.dao.deleteNote(note)
	}

	override fun getNote(): Flow<List<Note>> {
		return db.dao.getNote()
	}

}