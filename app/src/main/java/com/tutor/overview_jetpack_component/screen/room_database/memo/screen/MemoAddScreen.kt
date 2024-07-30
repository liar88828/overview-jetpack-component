package com.tutor.overview_jetpack_component.screen.room_database.memo.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tutor.overview_jetpack_component.screen.room_database.memo.persentation.MemoEvent
import com.tutor.overview_jetpack_component.screen.room_database.memo.persentation.MemoState
import com.tutor.overview_jetpack_component.screen.room_database.memo.screen.component.MyFloatingAdd

@Composable
fun MemoAddScreen(
	state: MemoState, onEvent: (MemoEvent) -> Unit, navController: NavHostController
) {
	fun handleSaveMemo() {
		onEvent(
			MemoEvent.SaveNote(
				state.title.value,
				state.description.value
			)
		)
		navController.popBackStack()
	}


	Scaffold(
		floatingActionButton = {
			MyFloatingAdd(
				state = state,
				navController = navController,
				onEvent = onEvent
			)
		}, modifier = Modifier.fillMaxSize()
	) { paddingValues ->
		Card(
			elevation = CardDefaults.cardElevation(
				defaultElevation = 6.dp
			),
			modifier = Modifier
				.padding(paddingValues)
				.fillMaxSize()
				.padding(50.dp),
			colors = CardDefaults.cardColors(
				containerColor = MaterialTheme.colorScheme.errorContainer,
			),
		) {
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(20.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp)
			) {
				OutlinedTextField(
					modifier = Modifier.fillMaxWidth(),
					value = state.title.value,
					onValueChange = {
						state.title.value = it
					},
					textStyle = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 17.sp),
					label = { Text("Title") },
					placeholder = { Text("Add Title") },
					shape = MaterialTheme.shapes.large,
					colors = TextFieldDefaults.colors(
						focusedContainerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
						focusedIndicatorColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
						unfocusedContainerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
						unfocusedIndicatorColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
						disabledContainerColor = Color.Transparent,
					),
					keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
				)
				OutlinedTextField(
					keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
					keyboardActions = KeyboardActions(onDone = { handleSaveMemo() }),
					colors = TextFieldDefaults.colors(
						focusedContainerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
						focusedIndicatorColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
						unfocusedContainerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
						unfocusedIndicatorColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
						disabledContainerColor = Color.Transparent,
					),
					shape = MaterialTheme.shapes.large,
					modifier = Modifier.fillMaxWidth(),
					value = state.description.value,
					onValueChange = {
						state.description.value = it
					},
					label = { Text("Description") },
					placeholder = { Text("Add Description") },
					textStyle = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 17.sp),
				)

				Button(
					modifier = Modifier.fillMaxWidth(),
					colors = ButtonDefaults.buttonColors(
						containerColor = MaterialTheme.colorScheme.error.copy(
							alpha = 0.8f
						)
					),
					onClick = { handleSaveMemo() }
				) { Text(text = "Save") }
			}
		}
	}
}

@Preview
@Composable
private fun MemoAddScreenPrev() {
	MemoAddScreen(
		state = MemoState(), onEvent = {}, navController = NavHostController(LocalContext.current)
	)
}