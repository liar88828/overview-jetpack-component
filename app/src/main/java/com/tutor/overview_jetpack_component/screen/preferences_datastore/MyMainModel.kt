package com.tutor.overview_jetpack_component.screen.preferences_datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyMainModel
@Inject
constructor(
	private val myPreferenceDataStore: MyPreferenceDataStore
) : ViewModel() {
	val isCompleted = myPreferenceDataStore.taskStatusFlow.map {
		it.isComplete
	}
	val priority = myPreferenceDataStore.taskStatusFlow.map {
		it.priority
	}

	fun updateIsComplete(isComplete: Boolean) {
		viewModelScope.launch {
			myPreferenceDataStore.updateIsComplete(isComplete)
		}
	}

	fun updateIsPriority(isPriority: Priority) {
		viewModelScope.launch {
			myPreferenceDataStore.updateIsPriority(isPriority)
		}
	}
}