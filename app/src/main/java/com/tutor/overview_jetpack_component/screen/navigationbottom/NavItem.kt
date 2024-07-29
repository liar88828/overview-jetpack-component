package com.tutor.overview_jetpack_component.screen.navigationbottom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
	val label: String,
	val icon: ImageVector,
	val route: String
)

val listNavItem = listOf(
	NavItem(
		label = "Home",
		icon = Icons.Default.Home,
		route = RScreen.HomeScreen.name
	),
	NavItem(
		label = "Profile",
		icon = Icons.Default.Person,
		route = RScreen.ProfileScreen.name
	),
	NavItem(
		label = "Setting",
		icon = Icons.Default.Settings,
		route = RScreen.SettingScreen.name
	),
)