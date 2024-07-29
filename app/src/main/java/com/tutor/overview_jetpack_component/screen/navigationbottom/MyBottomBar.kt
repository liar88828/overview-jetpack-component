package com.tutor.overview_jetpack_component.screen.navigationbottom

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
private fun MyMainScreen() {
	val navController = rememberNavController()


	Scaffold(
		bottomBar = {
			NavigationBar {
				val navBackStackEntry by navController.currentBackStackEntryAsState()
				val currentDestination = navBackStackEntry?.destination?.route
				listNavItem.forEach { item ->
					NavigationBarItem(
						selected = currentDestination == item.route,
						onClick = {
							navController.navigate(item.route) {
								popUpTo(navController.graph.findStartDestination().id) {
									saveState = true
								}
								launchSingleTop = true
								restoreState = true
							}
						},
						icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
						label = { Text(text = item.label) }
					)
				}
			}
		}
	) { paddingValues ->
		NavHost(
			navController = navController,
			startDestination = RScreen.HomeScreen.name,
			modifier = Modifier.padding(paddingValues)
		) {
			composable(route = RScreen.HomeScreen.name) { MyHomeScreen() }
			composable(route = RScreen.ProfileScreen.name) { MyProfileScreen() }
			composable(route = RScreen.SettingScreen.name) { MySettingScreen() }
		}
	}
}

@Preview
@Composable
private fun MyMainScreenPrev() {
	MyMainScreen()
}