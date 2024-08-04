package com.tutor.overview_jetpack_component.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyFloatingActionButton(onClick: () -> Unit) {
	FloatingActionButton(onClick = { onClick() }) {
		Icon(
			imageVector = Icons.Default.Add, contentDescription = "Add Icon"
		)

	}
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTopBar() {
	CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
		containerColor = MaterialTheme.colorScheme.primaryContainer,

		), title = {
		Text(text = "Weather App")
	},

		navigationIcon = {
			IconButton(onClick = { /*TODO*/ }) {
				Icon(
					imageVector = Icons.Default.Search, contentDescription = "Search Icon"
				)
			}
		})
}

@Composable
fun MyNavigationBar(modifier: Modifier) {
	NavigationBar() {
		NavigationBarItem(onClick = {}, selected = false, label = { Text(text = "Home") }, icon = {
			Icon(
				imageVector = Icons.Default.Home, contentDescription = "Home Icon"
			)
		})
		FloatingActionButton(modifier = modifier.padding(bottom = 8.dp), onClick = { /*TODO*/ }) {
			Icon(
				imageVector = Icons.Default.Public, contentDescription = "Public Icon"
			)
		}
		NavigationBarItem(onClick = {},
			selected = false,
			label = { Text(text = "Setting") },
			icon = {
				Icon(
					imageVector = Icons.Default.Settings, contentDescription = "setting Icon"
				)
			})
	}
}