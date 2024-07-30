package com.tutor.overview_jetpack_component.screen.room_database.memo.persentation

import com.tutor.overview_jetpack_component.screen.room_database.memo.data.Memo

sealed class MemoEvent {
	object sortNotes : MemoEvent()
	data class DeleteNote(val memo: Memo) : MemoEvent()
	data class SaveNote(val title: String, val description: String) : MemoEvent()

}

