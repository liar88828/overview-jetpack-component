package com.tutor.overview_jetpack_component.screen.retrofit.pokedex

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.screen.PokemonListScreen

@Composable
fun PokedexNav(modifier: Modifier) {
	val navComposable = rememberNavController()

	NavHost(
		navController = navComposable,
		startDestination = PokeRoute.PokemonListScreen.route,
	) {
		composable(PokeRoute.PokemonListScreen.route) {
			PokemonListScreen(navComposable)
		}
		composable(
			PokeRoute.PokemonDetailScreen.route + "/{pokemonName}",
			listOf(navArgument("pokemonName") {
				type = NavType.StringType
			})
		) {
			val pokemonName = remember {
				it.arguments?.getString("pokemonName")
			}

			PokemonDetailScreen(navComposable)
		}
	}

}

@Composable
fun PokemonDetailScreen(navComposable: NavHostController) {

}

sealed class PokeRoute(val route: String) {
	data object PokemonListScreen : PokeRoute("pokemon-list-screen")
	data object PokemonDetailScreen : PokeRoute("pokemon-detail-screen")

}