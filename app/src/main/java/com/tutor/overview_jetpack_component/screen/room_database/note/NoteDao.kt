package com.tutor.overview_jetpack_component.screen.room_database.note

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
	@Upsert
	suspend fun upsertNote(note: Note)

	@Delete
	suspend fun deleteNote(note: Note)

	@Query("SELECT * FROM note ORDER BY name ASC")
	fun getNote(): Flow<List<Note>>
}