package com.tutor.overview_jetpack_component.screen.room_database.memo.screen.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.tutor.overview_jetpack_component.screen.room_database.memo.persentation.MemoEvent
import com.tutor.overview_jetpack_component.screen.room_database.memo.persentation.MemoState

@Composable
fun MyFloatingAction(
	state: MemoState,
	navController: NavHostController,
	route: String
) {
	FloatingActionButton(
		containerColor = MaterialTheme.colorScheme.errorContainer,
		contentColor = MaterialTheme.colorScheme.error.copy(alpha = 0.8f),
		onClick = {
			state.title.value = ""
			state.description.value = ""
			navController.navigate(route)
		}) {
		Icon(
			imageVector = Icons.Rounded.Add,
			contentDescription = "Add Memo",
//			tint = MaterialTheme.colorScheme.errorContainer
		)
	}
}

@Composable
fun MyFloatingAdd(
	state: MemoState,
	navController: NavHostController,
	onEvent: (MemoEvent) -> Unit,
) {
	FloatingActionButton(
		containerColor = MaterialTheme.colorScheme.errorContainer,
		contentColor = MaterialTheme.colorScheme.error.copy(alpha = 0.8f),
		onClick = {
			onEvent(
				MemoEvent.SaveNote(
					state.title.value,
					state.description.value
				)
			)
			navController.popBackStack()
		}) {
		Icon(
			imageVector = Icons.Rounded.Check,
			contentDescription = "Save Memo",
			tint = Color.DarkGray
		)
	}
}

@Preview
@Composable
private fun MyFloatingActionPrev() {
	MyFloatingAction(
		state = MemoState(),
		navController = NavHostController(LocalContext.current),
		route = ""
	)
}