package com.tutor.overview_jetpack_component.screen.room_database.memo.persentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutor.overview_jetpack_component.screen.room_database.memo.data.Memo
import com.tutor.overview_jetpack_component.screen.room_database.memo.data.MemoDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//private val memoViewModel by viewModels<MemoViewModel>(
//	factoryProducer = {
//		object : ViewModelProvider.Factory {
//			override fun <T : ViewModel> create(modelClass: Class<T>): T {
//				return MemoViewModel(memoDatabase.dao) as T
//			}
//		}
//	}
//)
//private val memoDatabase by lazy {
//	Room.databaseBuilder(
//		applicationContext,
//		MemoDatabase::class.java,
//		"memos.db"
//	)
//		.build()
//}

class MemoViewModel(
	private val dao: MemoDao
) : ViewModel() {
	private val isShortedByDateAdded = MutableStateFlow(true)
	val _state = MutableStateFlow(MemoState())

	@OptIn(ExperimentalCoroutinesApi::class)
	private var memos = isShortedByDateAdded.flatMapLatest { sort ->
		if (sort) dao.getMemos()
		else dao.getMemoByTitle()
	}.stateIn(
		scope = viewModelScope,
		started = SharingStarted.WhileSubscribed(),
		initialValue = emptyList()
	)
	val state = combine(_state, isShortedByDateAdded, memos)
	{ state, isShortedByDateAdded, memos ->
		state.copy(memos = memos)
	}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MemoState())

	fun onEvent(event: MemoEvent) {
		when (event) {
			is MemoEvent.DeleteNote -> {
				viewModelScope.launch { dao.deleteMemo(event.memo) }
			}

			is MemoEvent.SaveNote -> {
				val memo = Memo(
					title = state.value.title.value,
					description = state.value.description.value,
					dateAdded = System.currentTimeMillis()
				)
				viewModelScope.launch { dao.upsertMemo(memo) }

				_state.update {
					it.copy(
						title = mutableStateOf(""),
						description = mutableStateOf("")
					)
				}
			}

			MemoEvent.sortNotes -> {
				isShortedByDateAdded.value = !isShortedByDateAdded.value
			}
		}
	}

}