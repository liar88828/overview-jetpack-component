package com.tutor.overview_jetpack_component.movieApp.utils

sealed class Screen(val route: String) {
	data object Home : Screen("main")
	data object Popular : Screen("popular")
	data object Upcoming : Screen("upcoming")
	data object Details : Screen("Details")

}