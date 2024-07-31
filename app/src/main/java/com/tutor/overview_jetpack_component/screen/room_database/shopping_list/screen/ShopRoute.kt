package com.tutor.overview_jetpack_component.screen.room_database.shopping_list.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.screen.detail.DetailShopScreen
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.screen.home.HomeShopScreen

enum class ShopRoute {
	Home,
	Detail

}

@Composable
fun ShoppingNavigation(navController: NavHostController) {
	NavHost(
		navController = navController,
		startDestination = ShopRoute.Home.name,
	) {
		composable(route = ShopRoute.Home.name) {
			HomeShopScreen(onNavigate = {
				navController.navigate(ShopRoute.Detail.name + "?id=$id")
			})
		}
		composable(
			route = ShopRoute.Detail.name + "?id={id}",
			arguments = listOf(navArgument("id") { type = androidx.navigation.NavType.IntType })
		) {
			val id = it.arguments?.getString("id")?.toInt() ?: -1
			DetailShopScreen(
				navigateUp = { navController.navigateUp() },
				id = id
			)
		}
	}

}