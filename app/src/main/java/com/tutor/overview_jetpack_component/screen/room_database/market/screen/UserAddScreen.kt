package com.tutor.overview_jetpack_component.screen.room_database.market.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tutor.overview_jetpack_component.screen.room_database.market.persentation.UserEvent
import com.tutor.overview_jetpack_component.screen.room_database.market.persentation.UserState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAddScreen(
	state: UserState,
	onEvent: (UserEvent) -> Unit,
	navController: NavHostController,
	modifier: Modifier = Modifier
) {
	Scaffold(
		modifier = modifier.fillMaxSize(),
		topBar = {
			CenterAlignedTopAppBar(
				title = { Text("Add User") },
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = MaterialTheme.colorScheme.primaryContainer,
				),
				navigationIcon = {
					IconButton(onClick = { navController.popBackStack() }) {
						Icon(
							imageVector = Icons.AutoMirrored.Filled.ArrowBack,
							contentDescription = "Back Icons"
						)
					}
				}
			)
		}) { paddingValues ->
		Box(
			modifier = modifier
				.padding(paddingValues)
				.fillMaxSize()
		) {
			Column(
				modifier = modifier
					.fillMaxSize()
					.padding(16.dp),
				verticalArrangement = Arrangement.spacedBy(16.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				OutlinedTextField(
					value = state.name,
					onValueChange = {
						onEvent(UserEvent.SetName(it))
					},
					label = { Text("Name") },
					placeholder = { Text("Add Name") },
					modifier = modifier.fillMaxWidth(),
					colors = OutlinedTextFieldDefaults.colors(
						focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
						unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
					),
					shape = MaterialTheme.shapes.extraLarge,
				)
				OutlinedTextField(
					value = state.age,
					onValueChange = {
						onEvent(UserEvent.SetAge(it))
					},
					shape = MaterialTheme.shapes.extraLarge,
					label = { Text("Age") },
					placeholder = { Text("Add Age") },
					modifier = modifier.fillMaxWidth(),
					colors = OutlinedTextFieldDefaults.colors(
						focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
						unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
					)
				)
				Button(
					onClick = {
						onEvent(UserEvent.SaveUser)
						navController.navigateUp()
					},
					modifier = modifier
						.fillMaxWidth()
				) {
					Row(
						modifier = modifier
							.padding(2.dp)
							.fillMaxWidth(),
						horizontalArrangement = Arrangement.Center,
					) {
						Text("Save", fontSize = 18.sp)
						Spacer(modifier = modifier.size(8.dp))
						Icon(
							imageVector = Icons.AutoMirrored.Filled.Send,
							contentDescription = "Back Icons"
						)
					}
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun UserAddScreenPrev() {
	UserAddScreen(
		state = UserState(),
		onEvent = {},
		navController = NavHostController(LocalContext.current)
	)

}