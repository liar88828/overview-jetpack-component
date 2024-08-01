package com.tutor.overview_jetpack_component.screen.room_database.product.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tutor.overview_jetpack_component.screen.room_database.product.persentation.ProductViewModel

@Composable
fun ProductNav(navController: NavHostController, viewModel: ProductViewModel) {
	val state by viewModel.state.collectAsState()

	NavHost(
		navController = navController,
		startDestination = ProductRoute.Main.route
	) {
		navigation(
			route = ProductRoute.Main.route,
			startDestination = ProductRoute.List.route
		) {
			composable(
				route = ProductRoute.List.route
			) {
				ProductListScreen(
					navController = navController,
					state = state,
					onEvent = viewModel::onEvent
				)
			}
			composable(route = ProductRoute.Form.route) {
				ProductFormScreen(
					navController = navController,
					state = state,
					onEvent = viewModel::onEvent
				)
			}
			composable(route = ProductRoute.Form.route) {
				ProductDetailScreen(
					navController = navController,
					state = state,
					onEvent = viewModel::onEvent
				)
			}
		}
	}
}

sealed class ProductRoute(val route: String) {
	data object List : ProductRoute("product")
	data object Form : ProductRoute("product-form")
	data object Detail : ProductRoute("product-detail")
	data object Edit : ProductRoute("product-edit")
	data object Main : ProductRoute("product-main")
}