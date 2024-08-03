package com.tutor.overview_jetpack_component.screen.room_database.note

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
	entities = [Note::class],
	version = 1
)
abstract class NoteDatabase : RoomDatabase() {
	abstract val dao: NoteDao
}
//private val noteDatabase by lazy {
//	Room.databaseBuilder(
//		applicationContext,
//		NoteDatabase::class.java,
//		"notes.db"
//	)
//		.build()
//}
//