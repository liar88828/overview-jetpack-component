package com.tutor.overview_jetpack_component.screen.room_database.contact

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyContactScreen(
	state: ContactState,
	onEvent: (ContactEvent) -> Unit,
	modifier: Modifier = Modifier
) {
	Scaffold(
		floatingActionButton = {
			FloatingActionButton(onClick = { onEvent(ContactEvent.ShowDialog) }) {
				Icon(imageVector = Icons.Default.Add, contentDescription = "Icon Add contact")
			}
		},
		modifier = modifier.padding(16.dp)
	) { paddingValue ->
		if (state.isAddingContact) {
			AddContactDialogScreen(state = state, onEvent = onEvent)
		}
		LazyColumn(
			modifier.fillMaxSize(),
			contentPadding = (paddingValue),
			verticalArrangement = Arrangement.spacedBy(16.dp),
		) {
			item {
				Row(
					modifier = modifier
						.fillMaxWidth()
						.horizontalScroll(rememberScrollState()),
//					verticalAlignment = Alignment.CenterVertically,
				) {
					SortType.entries.forEach { sortType ->
						Row(
							modifier = modifier
								.clickable {
									onEvent(ContactEvent.SortContacts(sortType))
								},
							verticalAlignment = Alignment.CenterVertically
						) {
							RadioButton(
								selected = state.sortType == sortType,
								onClick = {
									onEvent(ContactEvent.SortContacts(sortType))
								}
							)
							Text(text = sortType.name)
						}
					}
				}
			}

			items(state.contacts) { contact ->
				Row(modifier = modifier.fillMaxWidth()) {
					Column(modifier = modifier.weight(1f)) {
						Text(
							text = "${contact.firstName} - ${contact.lastName}",
							fontSize = 20.sp,
						)
						Text(text = contact.phoneNumber, fontSize = 12.sp)
					}
					IconButton(onClick = {
						onEvent(ContactEvent.DeleteContact(contact))
					}) {
						Icon(
							imageVector = Icons.Default.Delete,
							contentDescription = "Delete Contact"
						)
					}
				}
			}
		}
	}
}