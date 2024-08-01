package com.tutor.overview_jetpack_component.screen.room_database.market.persentation

sealed class UserRoute(val route: String) {
	data object List : UserRoute("user-list")
	data object Add : UserRoute("user-add")
	data object Main : UserRoute("user-main")
}