package com.tutor.overview_jetpack_component.screen.room_database.market.persentation

import com.tutor.overview_jetpack_component.screen.room_database.contact.SortType
import com.tutor.overview_jetpack_component.screen.room_database.market.data.User

sealed interface UserEvent {
	data class SortContacts(val sortType: SortType) : UserEvent
	object ShowDialog : UserEvent
	object HideDialog : UserEvent

	//
	object SaveUser : UserEvent
	data class DeleteUser(val user: User) : UserEvent
	data class SetAge(val age: String) : UserEvent
	data class SetName(val name: String) : UserEvent
	data class SortUser(val sort: Boolean) : UserEvent

}