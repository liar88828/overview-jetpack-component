package com.tutor.overview_jetpack_component.screen.room_database.market.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun create(user: User)

	@Upsert
	suspend fun upsert(user: User)

	@Update(onConflict = OnConflictStrategy.REPLACE)
	suspend fun update(user: User)

	@Delete
	suspend fun delete(user: User)

	@Query("SELECT * FROM user")
	fun allUser(): Flow<List<User>>

	@Query("SELECT * FROM user ORDER BY age ASC")
	fun allUserAge(): Flow<List<User>>

	@Query("SELECT * FROM user ORDER BY name ASC")
	fun allUserName(): Flow<List<User>>

	@Query("SELECT * FROM user WHERE id=:id")
	fun getUserById(id: Int): Flow<User>
}