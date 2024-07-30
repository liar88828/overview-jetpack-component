package com.tutor.overview_jetpack_component.screen.room_database.memo.persentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.tutor.overview_jetpack_component.screen.room_database.memo.data.Memo

data class MemoState(
	val memos: List<Memo> = emptyList(),
	val title: MutableState<String> = mutableStateOf(""),
	val description: MutableState<String> = mutableStateOf("")
)