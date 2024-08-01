package com.tutor.overview_jetpack_component.screen.room_database.market.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tutor.overview_jetpack_component.screen.room_database.market.persentation.UserRoute
import com.tutor.overview_jetpack_component.screen.room_database.market.persentation.UserViewModel

@Composable
fun UserNavigationScreen(
	navController: NavHostController,
	viewModel: UserViewModel
) {
//	val viewModel = viewModel<DetailViewModel>(factory = DetailViewModelFactory(id))
	val state by viewModel.state.collectAsState()

	NavHost(
		navController = navController,
		startDestination = UserRoute.Main.route
	) {
		navigation(
			route = UserRoute.Main.route,
			startDestination = UserRoute.List.route
		) {
			composable(route = UserRoute.List.route) {
				UserListScreen(
					navController = navController,
					onEvent = viewModel::onEvent,
					state = state
				)
			}
			composable(route = UserRoute.Add.route) {
				UserAddScreen(
					navController = navController,
					onEvent = viewModel::onEvent,
					state = state
				)
			}
		}
	}
}