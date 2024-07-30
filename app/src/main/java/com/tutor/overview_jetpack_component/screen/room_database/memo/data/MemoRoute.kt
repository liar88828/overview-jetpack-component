package com.tutor.overview_jetpack_component.screen.room_database.memo.data

sealed class MemoRoute(val route: String) {
	data object List : MemoRoute("MemoScreen")
	data object Add : MemoRoute("AddMemoScreen")

}