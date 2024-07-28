package com.tutor.overview_jetpack_component.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme

@Composable
fun MyFloatingAction(modifier: Modifier = Modifier) {
	Scaffold(
		floatingActionButton = {
			MyFloatingButton()
		}
	) { paddingValues ->
		Box(
			modifier = modifier
				.fillMaxSize()
				.padding(paddingValues),
			contentAlignment = Alignment.Center
		) {
			Text(
				text = "Text Screen",
				fontWeight = FontWeight.SemiBold,
				fontSize = 28.sp
			)
		}
	}

}

@Composable
private fun MyFloatingButton(modifier: Modifier = Modifier) {
	Column(
		verticalArrangement = Arrangement.spacedBy(8.dp),
		horizontalAlignment = Alignment.End
	) {
		SmallFloatingActionButton(
			containerColor = MaterialTheme.colorScheme.primaryContainer,
			contentColor = MaterialTheme.colorScheme.primary,
			onClick = {}) {
			Icon(
				imageVector = Icons.Default.Edit,
				contentDescription = "Edit Icons"
			)
		}
		FloatingActionButton(
			containerColor = MaterialTheme.colorScheme.primaryContainer,
			contentColor = MaterialTheme.colorScheme.primary,
			onClick = {}) {
			Icon(
				imageVector = Icons.Default.Edit,
				contentDescription = "Edit Icons"
			)
		}
		LargeFloatingActionButton(
			containerColor = MaterialTheme.colorScheme.primaryContainer,
			contentColor = MaterialTheme.colorScheme.primary,
			onClick = {}) {
			Icon(
				imageVector = Icons.Default.Edit,
				contentDescription = "Edit Icons"
			)
		}

		ExtendedFloatingActionButton(
			containerColor = MaterialTheme.colorScheme.primaryContainer,
			contentColor = MaterialTheme.colorScheme.primary,
			onClick = {},
			icon = { Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Icons") },
			text = { Text(text = "Edit") }
		)
	}
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MyFloatingActionPrev() {
	OverviewJetpackComponentTheme {
		MyFloatingAction()
	}
}
