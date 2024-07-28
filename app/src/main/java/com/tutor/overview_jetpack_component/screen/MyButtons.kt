package com.tutor.overview_jetpack_component.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme

@Composable
fun MyButtons(modifier: Modifier = Modifier) {
	Surface(modifier = modifier.fillMaxSize()) {
		Column(
			modifier = modifier.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.SpaceEvenly
		) {
			Button(onClick = {}) {
				Text(text = "Button")
				Spacer(modifier.size(ButtonDefaults.IconSpacing))
				Icon(imageVector = Icons.Default.Check, contentDescription = "Check Icons")
			}
			FilledTonalButton(onClick = {}) {
				Text(text = "FilledTonalButton")
				Spacer(modifier.size(ButtonDefaults.IconSpacing))
				Icon(imageVector = Icons.Default.Check, contentDescription = "Check Icons")
			}
			OutlinedButton(
				onClick = {},
			) {
				Text(text = "OutlinedButton")
				Spacer(modifier.size(ButtonDefaults.IconSpacing))
				Icon(imageVector = Icons.Default.Check, contentDescription = "Check Icons")
			}
			ElevatedButton(
				onClick = {},
			) {
				Text(text = "OutlinedButton")
				Spacer(modifier.size(ButtonDefaults.IconSpacing))
				Icon(imageVector = Icons.Default.Check, contentDescription = "Check Icons")
			}

			TextButton(onClick = {}) {
				Text(text = "TextButton")
				Spacer(modifier.size(ButtonDefaults.IconSpacing))
				Icon(imageVector = Icons.Default.Add, contentDescription = "Check Icons")
			}
			IconButton(onClick = {}) {
				Icon(
					imageVector = Icons.Default.Menu,
					contentDescription = "Add Icons"
				)
			}
			FloatingActionButton(onClick = {}) {
				Icon(
					imageVector = Icons.Default.Edit,
					contentDescription = "Add Icons"
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Preview(
	showBackground = true,
	uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun MyButtonsPrev() {
	OverviewJetpackComponentTheme {
		MyButtons()
	}
}