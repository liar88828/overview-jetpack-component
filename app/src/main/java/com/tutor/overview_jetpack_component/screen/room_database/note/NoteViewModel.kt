package com.tutor.overview_jetpack_component.screen.room_database.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteViewModel(
	private val repo: NoteRepo
) : ViewModel() {
	fun getNotes(): LiveData<List<Note>> {
		return repo.getNote().asLiveData(viewModelScope.coroutineContext)
	}

	fun upsertNote(note: Note) {
		viewModelScope.launch {
			repo.upsertNote(note)
		}
	}

	fun deleteNote(note: Note) {
		viewModelScope.launch {
			repo.deleteNote(note)
		}
	}

}