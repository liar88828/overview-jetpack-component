package com.tutor.overview_jetpack_component.screen.room_database.memo.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tutor.overview_jetpack_component.screen.room_database.memo.data.MemoRoute
import com.tutor.overview_jetpack_component.screen.room_database.memo.persentation.MemoViewModel

@Composable
fun MemoScreen(
	viewModel: MemoViewModel,
	navController: NavHostController
) {
	val state by viewModel.state.collectAsState()

	NavHost(
		navController = navController,
		startDestination = MemoRoute.List.route
	) {
		composable(route = MemoRoute.List.route)
		{
			MemoListScreen(
				state = state,
				navController = navController,
				onEvent = viewModel::onEvent
			)
		}
		composable(route = MemoRoute.Add.route) {
			MemoAddScreen(
				state = state,
				navController = navController,
				onEvent = viewModel::onEvent,
			)
		}
	}

}



