package com.tutor.overview_jetpack_component.screen.room_database.memo.screen.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tutor.overview_jetpack_component.screen.room_database.memo.persentation.MemoEvent

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTopAppbar(onEvent: (MemoEvent) -> Unit) {
	TopAppBar(
		colors = TopAppBarDefaults.topAppBarColors(
			containerColor = MaterialTheme.colorScheme.errorContainer,
		),
		title = {
			Text(
				"Note App",
//						fontSize = 17.sp,
				fontWeight = FontWeight.SemiBold,
			)
		},
		actions = {
			IconButton(onClick = { onEvent(MemoEvent.sortNotes) }) {
				Icon(
					imageVector = Icons.Rounded.KeyboardArrowDown,
					contentDescription = "Icon Sort",
					modifier = Modifier.size(35.dp)
				)
			}
		}
	)
}