package com.tutor.overview_jetpack_component.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme

@Composable
fun MyChips(modifier: Modifier = Modifier) {
	var selected by remember { mutableStateOf(false) }
	var enable by remember { mutableStateOf(true) }

	Surface(
		modifier = modifier.fillMaxSize(),
	) {
		Column(
			modifier = modifier.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.SpaceEvenly
		) {
			AssistChip(
				onClick = {
//					selected = !selected
				},
				label = { Text(text = "Assist Chip") },
				leadingIcon = {
					Icon(
						imageVector = Icons.Default.Add,
						contentDescription = "Icons Add"
					)
				}
			)

			FilterChip(
				selected = selected,
				onClick = { selected = !selected },
				label = {
					Text(text = "Filter Chip")
				},
				leadingIcon = if (selected) {
					{ Icon(imageVector = Icons.Filled.Done, contentDescription = "Icons Done") }
				} else {
					null
				})

			InputChip(
				selected = enable,
				onClick = { enable = !enable },
				label = { Text(text = "Input Chip") },
				avatar = {
					Icon(
						imageVector = Icons.Default.Person,
						contentDescription = "Icons Add"
					)
				},
				trailingIcon = {
					Icon(
						imageVector = Icons.Default.Close,
						contentDescription = "Icons Done"
					)
				}
			)


			SuggestionChip(
				onClick = {},
				label = { Text(text = "Suggestion Chip") }
			)
		}
	}
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MyChipsPrev() {
	OverviewJetpackComponentTheme {
		MyChips()
	}
}