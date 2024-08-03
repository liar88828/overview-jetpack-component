package com.tutor.overview_jetpack_component.screen.room_database.market.persentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tutor.overview_jetpack_component.screen.room_database.market.data.User
import com.tutor.overview_jetpack_component.screen.room_database.market.data.UserDao
import com.tutor.overview_jetpack_component.screen.room_database.market.data.UserGraph
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//inner
//val userViewModel = viewModel(modelClass = UserViewModel::class.java)

class UserViewModel(
	private val dao: UserDao = UserGraph.dao
//	private val dao:MyApplication.userDatabase.dao
) : ViewModel() {
	private val _sort = MutableStateFlow(true)
	private val _state = MutableStateFlow(UserState())

	@OptIn(ExperimentalCoroutinesApi::class)
	private var _user = _sort.flatMapLatest { sort ->
		if (sort) dao.allUserName()
		else dao.allUserAge()
	}.stateIn(
		scope = viewModelScope,
		started = SharingStarted.WhileSubscribed(),
		initialValue = emptyList()
	)

	//
	val state = combine(_state, _sort, _user) { state, sortType, user ->
		state.copy(
			users = user
		)
	}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserState())

	//
	fun onEvent(event: UserEvent) {
		when (event) {
			is UserEvent.DeleteUser -> {
				viewModelScope.launch { dao.delete(event.user) }
			}

			is UserEvent.SaveUser -> {
				val name = state.value.name
				val age = state.value.age

				if (age.isBlank() || name.isBlank()) {
					return
				}
				val user = User(
					name = name, age = age
				)

				viewModelScope.launch {
					dao.upsert(user)
				}
				_state.update {
					it.copy(
						name = "",
						age = ""
					)
				}
			}

			is UserEvent.SetAge -> {
				_state.update { it.copy(age = event.age) }
			}

			is UserEvent.SetName -> {
				_state.update { it.copy(name = event.name) }
			}

			is UserEvent.SortUser -> {
				_sort.value = event.sort
			}

			UserEvent.HideDialog -> TODO()
			UserEvent.ShowDialog -> TODO()
			is UserEvent.SortContacts -> TODO()
		}

	}
}

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(
	private val dao: UserDao
) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return UserViewModel(dao) as T
	}
}
