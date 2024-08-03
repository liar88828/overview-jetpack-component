package com.tutor.overview_jetpack_component.MVI.notes.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface NoteDao {

	@Upsert
	suspend fun upsertNote(note: NoteEntity)

	@Query("SELECT * FROM noteentity ")
	suspend fun getAllNotes(): List<NoteEntity>

	@Delete
	suspend fun deleteNote(note: NoteEntity)
}