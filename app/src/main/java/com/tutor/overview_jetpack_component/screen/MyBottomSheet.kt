package com.tutor.overview_jetpack_component.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet(modifier: Modifier = Modifier) {
	val sheetState = rememberModalBottomSheetState()
	val scope = rememberCoroutineScope()
	var showBottomSheet by remember { mutableStateOf(false) }


	Scaffold {
		if (showBottomSheet) {
			ModalBottomSheet(
				onDismissRequest = { showBottomSheet = false },
				sheetState = sheetState,
				) {
				Column(
					modifier = modifier
						.fillMaxWidth()
						.padding(
							horizontal = 16.dp,
							vertical = 8.dp,
						),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					Text(
						text = "Modal is a term commonly used in web and software development to refer to a pop-up window or dialog that appears on top of the main content. Learn more about modals and how they enhance user experience on websites and applications.",
						fontSize = 18.sp
					)
					Spacer(modifier = modifier.size(8.dp))
					Button(onClick = {
						scope.launch {
							sheetState.hide()
						}.invokeOnCompletion {
							if (!sheetState.isVisible) {
								showBottomSheet = false
							}
						}
					}) {
						Text(text = "Hide")
					}
				}
			}
		}
		Box(
			modifier = modifier
				.fillMaxSize()
				.padding(it),
			contentAlignment = Alignment.Center
		) {
			Button(onClick = { showBottomSheet = true }) {
				Text(text = "Show Bottom Sheet")
			}
		}
	}
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MyBottomSheetScreen() {
	OverviewJetpackComponentTheme {
		MyBottomSheet()
	}
}