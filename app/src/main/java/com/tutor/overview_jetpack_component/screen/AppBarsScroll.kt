package com.tutor.overview_jetpack_component.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarsScroll(modifier: Modifier = Modifier) {
	val scrollBehavior = TopAppBarDefaults
//		.enterAlwaysScrollBehavior()// will remove the appbar at scroll
//		.pinnedScrollBehavior()
		.exitUntilCollapsedScrollBehavior()// if scroll bottom appbar will exit when scroll up will show smooth
	Scaffold(
		modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//		containerColor = MaterialTheme.colorScheme.primaryContainer,
		topBar = { MyTopBar(scrollBehavior) },
		bottomBar = { MyBottomBar() }
	) { paddingValues ->
//		Box(
//			modifier = modifier
//				.fillMaxSize()
//				.padding(paddingValues),
//			contentAlignment = Alignment.Center
//		) {
//			Text(
//				text = "Text Center",
//				fontFamily = FontFamily.Monospace,
//				fontSize = 32.sp,
//				fontWeight = FontWeight.SemiBold
//			)
//		}
		LazyColumn(
			modifier = modifier
				.fillMaxSize()
				.padding(paddingValues),
			contentPadding = PaddingValues(8.dp),
			verticalArrangement = Arrangement.spacedBy(8.dp)
		) {
			items(20) {
				Card(modifier = modifier.fillParentMaxWidth()) {
					Text(
						modifier = modifier.padding(8.dp),
						text = "Item $it",
						fontSize = 20.sp
					)
				}
			}
		}
	}
}

@Composable
private fun MyBottomBar() {
	BottomAppBar(
		containerColor = MaterialTheme.colorScheme.primaryContainer,
		actions = {
			IconButton(onClick = {}) {
				Icon(imageVector = Icons.Default.Check, contentDescription = "Home")
			}
			IconButton(onClick = {}) {
				Icon(imageVector = Icons.Default.Edit, contentDescription = "Home")
			}
			IconButton(onClick = {}) {
				Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Home")
			}
			IconButton(onClick = {}) {
				Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "Home")
			}
		},
		floatingActionButton = {
			FloatingActionButton(
				containerColor = MaterialTheme.colorScheme.primary,
				onClick = {}) {
				Icon(
					imageVector = Icons.Default.Add,
					contentDescription = "Menu",
				)
			}
		},
	)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MyTopBar(scrollBehavior: TopAppBarScrollBehavior) {
	CenterAlignedTopAppBar(
		scrollBehavior = scrollBehavior,
//	LargeTopAppBar(
//	MediumTopAppBar(
//	TopAppBar(
		colors = TopAppBarDefaults.topAppBarColors(
			containerColor = MaterialTheme.colorScheme.primaryContainer,
		),
		navigationIcon = {
			IconButton(onClick = {}) {
				Icon(
					imageVector = Icons.AutoMirrored.Filled.ArrowBack,
					contentDescription = "Menu Icons"
				)
			}
		},
		actions = {
			IconButton(onClick = {}) {
				Icon(
					imageVector = Icons.Filled.Menu,
					contentDescription = "Icons Menu"
				)
			}
		},
		title = {
			Text(
				text = "Top App Bar",
				maxLines = 1,
				overflow = TextOverflow.Ellipsis
			)
		}
	)
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppBarsScrollPrev() {
	OverviewJetpackComponentTheme {
		AppBarsScroll()
	}
}