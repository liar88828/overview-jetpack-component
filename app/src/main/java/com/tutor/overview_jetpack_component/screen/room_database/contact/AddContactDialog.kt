package com.tutor.overview_jetpack_component.screen.room_database.contact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddContactDialogScreen(
	state: ContactState,
	onEvent: (ContactEvent) -> Unit,
	modifier: Modifier = Modifier
) {
	AlertDialog(
		modifier = modifier,
		onDismissRequest = { onEvent(ContactEvent.HideDialog) },
//		dismissButton = { onEvent(ContactEvent.HideDialog) },
		confirmButton = { Button(onClick = { onEvent(ContactEvent.SaveContact) }) { Text(text = "Save Contact") } },
		icon = {
			Icon(
				imageVector = Icons.Default.Delete,
				contentDescription = "Delete Icons"
			)
		},
		title = { Text(text = "Alert Dialog") },
		text = {
			Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
				TextField(
					value = state.firstName,
					onValueChange = { onEvent(ContactEvent.SetFirstName(it)) },
					placeholder = { Text(text = "First Name") }
				)
				TextField(
					value = state.lastName,
					onValueChange = { onEvent(ContactEvent.SetLastName(it)) },
					placeholder = { Text(text = "Last Name") }
				)

				TextField(
					value = state.phoneNumber,
					onValueChange = { onEvent(ContactEvent.SetPhoneNumber(it)) },
					placeholder = { Text(text = "Phone Number") }
				)
			}
		}
	)

}