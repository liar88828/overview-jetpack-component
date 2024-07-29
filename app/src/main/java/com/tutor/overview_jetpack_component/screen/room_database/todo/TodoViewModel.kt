package com.tutor.overview_jetpack_component.screen.room_database.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutor.overview_jetpack_component.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class TodoViewModel : ViewModel() {
	val todoDao = MyApplication.todoDatabase.dao
	val todoList = todoDao.getAllTodo()

	fun addTodo(todo: String) {
		viewModelScope.launch(Dispatchers.IO) {
			todoDao.addTodo(
				Todo(
					title = todo,
					createAt = Date.from(Instant.now())
				)
			)
		}
	}

	fun deleteTodo(id: Int) {
		viewModelScope.launch(Dispatchers.IO) {
			todoDao.deleteTodo(id)
		}
	}

}