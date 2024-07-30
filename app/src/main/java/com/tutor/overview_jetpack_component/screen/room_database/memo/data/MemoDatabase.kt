package com.tutor.overview_jetpack_component.screen.room_database.memo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
	entities = [Memo::class],
	version = 1
)
abstract class MemoDatabase : RoomDatabase() {
	abstract val dao: MemoDao
}