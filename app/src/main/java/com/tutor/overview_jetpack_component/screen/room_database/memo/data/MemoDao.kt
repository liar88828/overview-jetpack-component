package com.tutor.overview_jetpack_component.screen.room_database.memo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
	@Delete
	suspend fun deleteMemo(memo: Memo)

	@Update
	suspend fun updateMemo(memo: Memo)

	@Insert
	suspend fun insertMemo(memo: Memo)

	@Upsert
	suspend fun upsertMemo(memo: Memo)

	@Query("SELECT * FROM memo ORDER BY dateAdded ASC")
	fun getMemos(): Flow<List<Memo>>

	@Query("SELECT * FROM memo ORDER BY title ASC ")
	fun getMemoByTitle(): Flow<List<Memo>>
}