package com.tutor.overview_jetpack_component.MVI.notes.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
	entities = [NoteEntity::class],
	version = 1
)
abstract class NoteDatabase : RoomDatabase() {
	abstract val dao: NoteDao

}