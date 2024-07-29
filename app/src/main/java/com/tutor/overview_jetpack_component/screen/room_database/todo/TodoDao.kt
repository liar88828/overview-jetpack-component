package com.tutor.overview_jetpack_component.screen.room_database.todo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
	@Query("SELECT * FROM Todo")
	fun getAllTodo(): LiveData<List<Todo>>

	@Insert
	fun addTodo(todo: Todo)

	//	@Delete()
	@Query("DELETE FROM Todo WHERE id= :id")
	fun deleteTodo(id: Int)
}