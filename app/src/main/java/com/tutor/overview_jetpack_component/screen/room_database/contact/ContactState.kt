package com.tutor.overview_jetpack_component.screen.room_database.contact

data class ContactState(
	val contacts: List<Contact> = emptyList(),
	val firstName: String = "",
	val lastName: String = "",
	val phoneNumber: String = "",
	val isAddingContact: Boolean = false,
	val sortType: SortType = SortType.FIRST_NAME
)
