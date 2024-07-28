package com.tutor.overview_jetpack_component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
fun MySwitch(modifier: Modifier = Modifier) {
	Surface(modifier = modifier.fillMaxSize()) {
		Column(
			modifier = modifier.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			SimpleSwitch()
			IconSwitch()
			ColorSwitch()
		}
	}
}

@Composable
fun SimpleSwitch(modifier: Modifier = Modifier) {
	var checked by remember { mutableStateOf(true) }
	Switch(checked = checked, onCheckedChange = { checked = it })
}

@Composable
fun IconSwitch(modifier: Modifier = Modifier) {
	var checked by remember { mutableStateOf(true) }
	Switch(
		checked = checked, onCheckedChange = { checked = it },
		thumbContent = {
			if (checked) {
				Icon(
					imageVector = Icons.Default.Check,
					contentDescription = "Check Icons",
					modifier = modifier.size(SwitchDefaults.IconSize)
				)
			} else {
				null
			}
		}
	)
}

@Composable
fun ColorSwitch(modifier: Modifier = Modifier) {
	var checked by remember { mutableStateOf(true) }
	Switch(
		checked = checked,
		onCheckedChange = { checked = it },
		colors = SwitchDefaults.colors(
			checkedThumbColor = MaterialTheme.colorScheme.primary,
			checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
			uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
			uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
		)
	)
}

@Preview(showBackground = true)
@Composable
private fun MySwitchPrev() {
	OverviewJetpackComponentTheme {
		MySwitch()
	}
}
