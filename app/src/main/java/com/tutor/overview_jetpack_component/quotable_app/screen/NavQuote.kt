package com.tutor.overview_jetpack_component.quotable_app.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tutor.overview_jetpack_component.quotable_app.persentation.QuoteViewModel
import com.tutor.overview_jetpack_component.quotable_app.screen.Home.QuoteHomeScreen

@Composable
fun NavQuote(
	viewModel: QuoteViewModel,
	navController: NavHostController,
	modifier: Modifier = Modifier,
) {

	NavHost(
		navController = navController,
		startDestination = QuoteRoute.Home.route,
	) {
		composable(
			route = QuoteRoute.Home.route,
		) {
			QuoteHomeScreen(
				viewModel = viewModel,
				navController = navController,
				modifier = modifier
			)
		}
	}
}

