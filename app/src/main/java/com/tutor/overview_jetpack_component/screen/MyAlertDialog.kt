package com.tutor.overview_jetpack_component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme

@Composable
fun MyAlertDialog(modifier: Modifier = Modifier) {
	var openAlertDialog by remember { mutableStateOf(false) }
	var openDialog by remember { mutableStateOf(false) }
	Surface(modifier = modifier.fillMaxSize()) {
		Column(
			modifier = modifier.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Button(onClick = { openAlertDialog = true }) { Text(text = "Open Alert Dialog") }
			Spacer(modifier = modifier.size(8.dp))
			Button(onClick = { openDialog = true }) { Text(text = "Open Dialog") }
			AlertDialogTest(
				openDialog = openAlertDialog,
				onClose = { openAlertDialog = false })
			DialogExample(openDialog = openDialog, onClose = { openDialog = false })
		}
	}
}

@Composable
fun AlertDialogTest(
	openDialog: Boolean, onClose: () -> Unit, modifier: Modifier = Modifier
) {
	if (openDialog) {
		AlertDialog(
			onDismissRequest = { onClose() },
			confirmButton = { Button(onClick = { }) { Text(text = "Confirm") } },
			dismissButton = { Button(onClick = { onClose() }) { Text(text = "Dismiss") } },
			icon = {
				Icon(
					imageVector = Icons.Default.Delete,
					contentDescription = "Delete Icons"
				)
			},
			title = { Text(text = "Alert Dialog") },
			text = { Text(text = "This is an example of an alert dialog with buttons.") }
		)

	}
}

@Composable
fun DialogExample(
	openDialog: Boolean,
	onClose: () -> Unit,
	modifier: Modifier = Modifier
) {
	if (openDialog) {
		Dialog(onDismissRequest = { onClose() }) {
			Card {
				Text(
					modifier = modifier.padding(16.dp),
					text = "This is A minimal dialog."
				)
			}
		}
	}

}

@Preview(showBackground = true)
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MyAlertDialogPrev() {
	OverviewJetpackComponentTheme {
		MyAlertDialog()
	}
}

@Preview(showBackground = true)
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AlertDialogTestPrev() {
	OverviewJetpackComponentTheme {
		AlertDialogTest(openDialog = true, onClose = {})
	}
}

@Preview(showBackground = true)
@Composable
private fun DialogExamplePrev() {
	OverviewJetpackComponentTheme {
		DialogExample(openDialog = true, onClose = {})
	}
}