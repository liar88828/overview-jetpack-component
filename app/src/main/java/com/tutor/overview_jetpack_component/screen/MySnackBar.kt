package com.tutor.overview_jetpack_component.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme
import kotlinx.coroutines.launch

@Composable
fun SnackBarBasic(modifier: Modifier = Modifier) {
	val scope = rememberCoroutineScope()
	val snackbarHostState = remember { SnackbarHostState() }


	Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { paddingValue ->
		Box(
			modifier = modifier
				.fillMaxSize()
				.padding(paddingValue),
			contentAlignment = Alignment.Center
		) {
			Button(
				onClick = {
					scope.launch { snackbarHostState.showSnackbar("Basic SnackBar") }
				}) {
				Text(text = "Show SnackBar")
			}
		}
	}

}

@Composable
fun SnackBarAction(modifier: Modifier = Modifier) {
	val scope = rememberCoroutineScope()
	val snackbarHostState = remember { SnackbarHostState() }

	fun handlerSnackbar(): Unit {
		scope.launch {
			val result = snackbarHostState.showSnackbar(
				message = "Show with Action",
				actionLabel = "DONE",
				duration = SnackbarDuration.Short
			)
			when (result) {
				SnackbarResult.ActionPerformed -> {}

				SnackbarResult.Dismissed -> {
				}
			}
		}
	}

	Scaffold(
		snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
	) { paddingValue ->
		Box(
			modifier = modifier
				.fillMaxSize()
				.padding(paddingValue),
			contentAlignment = Alignment.Center
		) {
			Button(onClick = { handlerSnackbar() }) {
				Text(text = "Show Snackbar Action ")
			}
		}
	}
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MySnackBarPrev() {
	OverviewJetpackComponentTheme {
//		SnackBarBasic()
		SnackBarAction()
	}
}