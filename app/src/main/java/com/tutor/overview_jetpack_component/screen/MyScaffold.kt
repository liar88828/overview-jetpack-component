package com.tutor.overview_jetpack_component.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold(name: String, modifier: Modifier = Modifier) {
	var count by remember { mutableIntStateOf(0) }

	Scaffold(
		topBar = {
			TopAppBar(
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = MaterialTheme.colorScheme.primaryContainer
				),
				title = { Text(text = "Hello $name!") },
			)
		},
		bottomBar = {
			BottomAppBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
				Text(
					modifier = modifier.fillMaxWidth(),
					text = "Bottom App Bar",
					textAlign = TextAlign.Center,
				)
			}
		},
		floatingActionButton = {
			FloatingActionButton(onClick = {
				count++
			}) {
				Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icons")
			}
		}
	)
	{
		Column(
			modifier = modifier.padding(it),
			verticalArrangement = Arrangement.spacedBy(8.dp)
		) {
			Text(
				modifier = modifier.padding(8.dp),
				text = """
				Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vel egestas dolor, nec dignissim metus. Donec augue elit, rhoncus ac sodales id, porttitor vitae est. Donec laoreet rutrum libero sed pharetra.

                Donec vel egestas dolor, nec dignissim metus. Donec augue elit, rhoncus ac sodales id, porttitor vitae est. Donec laoreet rutrum libero sed pharetra. Duis a arcu convallis, gravida purus eget, mollis diam.
 
 
                you have pressed the floating action button ${count} times
		""".trimIndent()
			)
		}
	}
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
	OverviewJetpackComponentTheme {
		MyScaffold("Android")
	}
}