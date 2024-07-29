package com.tutor.overview_jetpack_component.screen.preferences_datastore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MyPreferenceScreen(
	mainModel: MyMainModel,
	selectedPriority: Priority,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier.fillMaxWidth(.7f),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
	) {
		Priority.entries.forEach { priority ->
			Text(
				text = priority.name,
				maxLines = 1,
				overflow = TextOverflow.Ellipsis
			)

			RadioButton(
				selected = priority == selectedPriority,
				onClick = { mainModel.updateIsPriority(priority) },
				colors = RadioButtonDefaults.colors(
					selectedColor = priority.color,
				)
			)
		}
	}
}