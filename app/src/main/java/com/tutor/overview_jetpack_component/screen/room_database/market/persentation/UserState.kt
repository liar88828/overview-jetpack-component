package com.tutor.overview_jetpack_component.screen.room_database.market.persentation

import com.tutor.overview_jetpack_component.screen.room_database.market.data.User

data class UserState(
	val users: List<User> = emptyList(),
	val name: String = "",
	val age: String = "",
)