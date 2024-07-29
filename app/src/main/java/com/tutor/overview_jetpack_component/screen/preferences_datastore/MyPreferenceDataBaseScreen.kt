package com.tutor.overview_jetpack_component.screen.preferences_datastore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyPreferenceDataBaseScreen(viewModel: MyMainModel) {
	val selectedIsComplete by viewModel.isCompleted.collectAsState(initial = false)
	val selectedPriority by viewModel.priority.collectAsState(initial = Priority.Low)
	val isCompletedStatus = if (selectedIsComplete) "Yes" else "No"
	val priorityStatus = when (selectedPriority) {
		Priority.High -> "High"
		Priority.Medium -> "Medium"
		Priority.Low -> "Low"
	}
	Surface(
		modifier = Modifier.fillMaxSize(),
	) {
		Column(
			modifier = Modifier.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Text(
				text = "Task Status",
				fontWeight = FontWeight.Bold,
				fontSize = 22.sp,
				textDecoration = TextDecoration.Underline
			)
			Spacer(modifier = Modifier.size(16.dp))
			Text(
				text = "complete $isCompletedStatus",
				fontSize = 22.sp
			)
			Switch(
				checked = selectedIsComplete,
				onCheckedChange = { viewModel.updateIsComplete(it) }
			)
			HorizontalDivider(
				modifier = Modifier
					.fillMaxWidth(.7f)
					.padding(vertical = 8.dp)
			)
			Text(
				text = "Priority: $priorityStatus",
				fontSize = 22.sp
			)

			MyPreferenceScreen(
				mainModel = viewModel,
				selectedPriority = selectedPriority
			)
			HorizontalDivider(
				modifier = Modifier
					.fillMaxWidth(.7f)
					.padding(vertical = 8.dp)
			)
		}
	}
}