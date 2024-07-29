package com.tutor.overview_jetpack_component.screen.navigationbottom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
fun MyHomeScreen(modifier: Modifier = Modifier) {
	Box(
		modifier = modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
//
//		var selectedItem by remember { mutableIntStateOf(0) }
//		val items = listOf("Home", "Search", "Settings")
//		val icons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)
//		NavigationRail {
//			items.forEachIndexed { index, item ->
//				NavigationRailItem(
//					icon = { Icon(icons[index], contentDescription = item) },
//					label = { Text(item) },
//					selected = selectedItem == index,
//					onClick = { selectedItem = index }
//				)
//			}
//		}
		Text(
			text = "Home Screen",
			fontFamily = FontFamily.Serif,
			fontSize = 22.sp
		)
	}
}