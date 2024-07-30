package com.tutor.overview_jetpack_component.screen.room_database.memo.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tutor.overview_jetpack_component.screen.room_database.memo.data.Memo
import com.tutor.overview_jetpack_component.screen.room_database.memo.data.MemoRoute
import com.tutor.overview_jetpack_component.screen.room_database.memo.persentation.MemoEvent
import com.tutor.overview_jetpack_component.screen.room_database.memo.persentation.MemoState
import com.tutor.overview_jetpack_component.screen.room_database.memo.screen.component.MyFloatingAction
import com.tutor.overview_jetpack_component.screen.room_database.memo.screen.component.MyTopAppbar

@Composable
fun MemoListScreen(
	state: MemoState,
	onEvent: (MemoEvent) -> Unit,
	navController: NavHostController
) {
	Scaffold(
		floatingActionButton = {
			MyFloatingAction(state, navController, MemoRoute.Add.route)
		},
		topBar = { MyTopAppbar(onEvent) }
	) { paddingValue ->
		LazyColumn(
			contentPadding = paddingValue,
			modifier = Modifier
				.fillMaxSize()
				.padding(8.dp),
			verticalArrangement = Arrangement.spacedBy(8.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			if (state.memos.isEmpty()) {
				item {
					Button(
						onClick = { navController.navigate(MemoRoute.Add.route) },
						colors = ButtonDefaults.buttonColors(
							containerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
						)
					) {
						Text(text = "Is Not Available")
					}
				}
			}
			items(state.memos) {
				MemoItemScreen(it, onEvent)
			}
		}
	}
}

@Composable
fun MemoItemScreen(
	item: Memo,
	onEvent: (MemoEvent) -> Unit,
) {
	Card(
		elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
		colors = CardDefaults.cardColors(
			containerColor = MaterialTheme.colorScheme.errorContainer,
		),
		modifier = Modifier
			.fillMaxWidth()
			.border(
				width = 1.dp,
				color = MaterialTheme.colorScheme.error,
				shape = RoundedCornerShape(10.dp)
			)
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(8.dp)
		) {
			Column(modifier = Modifier.weight(1f)) {
				Text(
					item.title,
					fontSize = 18.sp,
					fontWeight = FontWeight.SemiBold,
					color = MaterialTheme.colorScheme.onSecondaryContainer
				)
				Spacer(modifier = Modifier.height(8.dp))
				Text(
					item.description,
					fontSize = 14.sp,
					fontWeight = FontWeight.SemiBold,
					color = MaterialTheme.colorScheme.onSecondaryContainer
				)
			}
			SmallFloatingActionButton(
				containerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.8f),
				onClick = { onEvent(MemoEvent.DeleteNote(item)) }) {
				Icon(
					imageVector = Icons.Rounded.Delete,
					contentDescription = "Icon Delete",
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun MemoItemScreenPrev() {
	MemoItemScreen(
		item = Memo(
			title = "Title",
			description = "Description lorem ipsum dolor sit amet Vitae vitae Class bi ben dum",
			dateAdded = 123456789
		),
		onEvent = {}
	)
}

@Preview(showBackground = true)
@Composable
private fun MemoScreenListPrev() {
	Box(
		modifier = Modifier.fillMaxSize()
	) {
		MemoListScreen(
			state = MemoState(),
			onEvent = {},
			navController = NavHostController(LocalContext.current)
		)
	}

}

