package com.tutor.overview_jetpack_component.screen.retrofit.pokedex.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tutor.overview_jetpack_component.R
import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.PokeRoute
import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.data.models.PokeListEntry
import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.persentation.PokemonListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
	navController: NavHostController,
	viewModel: PokemonListViewModel = hiltViewModel(),
	modifier: Modifier = Modifier
) {
	var searchData by remember {
		mutableStateOf("")
	}

	val pokemonList = remember {
		viewModel.pokemonList
	}


	Scaffold(
		bottomBar = {
			NavigationBar {
				NavigationBarItem(
					selected = false,
					onClick = {},
					icon = {
						Icon(imageVector = Icons.Default.Home, contentDescription = "Home Icon")
					}
				)

				NavigationBarItem(
					selected = false,
					onClick = {},
					icon = {
						Icon(
							imageVector = Icons.Default.AccountBox,
							contentDescription = "Account Box"
						)
					}
				)

				FloatingActionButton({}) {
					Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon")
				}
				NavigationBarItem(
					selected = false,
					onClick = {},
					icon = {
						Icon(imageVector = Icons.Default.Public, contentDescription = "Globe Icon")
					}
				)
				NavigationBarItem(
					selected = false,
					onClick = {},
					icon = {
						Icon(
							imageVector = Icons.Default.Settings,
							contentDescription = "Setting icon"
						)
					}
				)
			}
		},
		floatingActionButton = {
			FloatingActionButton({}) {
				Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
			}
		},
		topBar = {
			CenterAlignedTopAppBar(title = { Text("Pokédex") })
		},
		modifier = modifier.fillMaxSize(),
	) { paddingValue ->
		Surface(
			modifier = modifier
				.padding(paddingValue)
				.fillMaxSize()
				.padding(18.dp), color = MaterialTheme.colorScheme.background
		) {
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.spacedBy(12.dp)
			) {
				Image(
					painter = painterResource(id = R.drawable.pokeapi_logo),
					contentDescription = "Android logo",
					modifier = modifier
						.fillMaxWidth()
						.size(100.dp)

				)
				SearchBar(search = searchData, onSearch = { searchData = it }

				)

//				LazyColumn {
//					items(pokemonList) {pokemon->
//						PokeDexEntry(
//							entry = PokeListEntry(
//								pokemonName = pokemon.pokemonName,
//								imageUrl = pokemon.imageUrl,
//								number = pokemon.number,
//							),
//							navComposable = navController
//
//						)
//					}
//				}
			}
		}
	}
}

@Composable
fun PokemonList(
	navController: NavHostController,
	viewModel: PokemonListViewModel = hiltViewModel(),
	modifier: Modifier = Modifier
) {

	val pokemonList by remember { viewModel.pokemonList }
	val endReached by remember { viewModel.endReached }
	val loadError by remember { viewModel.loadError }
	val isLoading by remember { viewModel.isLoading }

	LazyVerticalGrid(
		modifier = modifier.fillMaxSize(),
		columns = GridCells.Fixed(2),
		contentPadding = PaddingValues(6.dp),
		horizontalArrangement = Arrangement.spacedBy(6.dp),
		verticalArrangement = Arrangement.spacedBy(6.dp),
	) {
		items(pokemonList, key = { it.pokemonName }) {
			PokeDexEntry(
				entry = it,
				navComposable = navController
			)
		}
	}

}

@Composable
fun SearchBar(
	search: String = "", onSearch: (String) -> Unit = {}, modifier: Modifier = Modifier
) {
	OutlinedTextField(shape = MaterialTheme.shapes.extraLarge,
		colors = OutlinedTextFieldDefaults.colors(
			focusedBorderColor = Color.Transparent,
			unfocusedBorderColor = Color.Transparent,
			focusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
			unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
		),
		value = search,
		onValueChange = { onSearch(it) },
		modifier = modifier.fillMaxWidth(),
		leadingIcon = { },
		trailingIcon = { },
		maxLines = 1,
		singleLine = true,
		placeholder = {
			Text(
				text = "Search....",
				fontSize = 24.sp,
				style = MaterialTheme.typography.bodyMedium,
			)
		})
}

@Composable
fun PokeDexEntry(
	entry: PokeListEntry,
	navComposable: NavHostController,
//	viewModel: PokemonListViewModel,
	modifier: Modifier = Modifier
) {

	Card(
		modifier = modifier.clickable(onClick = {
			navComposable.navigate(
				PokeRoute.PokemonDetailScreen.route + "/${entry.pokemonName}"
			)
		}),
		elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),

		) {

		Image(
			painter = painterResource(id = R.drawable.ic_launcher_foreground),
			contentDescription = "Android logo",
			modifier = modifier.fillMaxWidth()
		)
		Column(
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = entry.pokemonName,
				fontSize = 24.sp,
				style = MaterialTheme.typography.bodyMedium,
			)
			Text(
				text = entry.number.toString(),
				fontSize = 24.sp,
				style = MaterialTheme.typography.bodyMedium,
			)
		}
	}
}

//@Preview(showBackground = true)
//@Composable
//private fun PokemonListScreenPrev() {
//	PokemonListScreen(
//		navController = rememberNavController(),
//		viewModel = PokemonListViewModel
//	)
//}

@Preview(showBackground = true)
@Composable
private fun PokeDexEntryPrev() {
	PokeDexEntry(
		entry = PokeListEntry(
			pokemonName = "Pikachu",
			imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
			number = 25,

			),
		navComposable = rememberNavController(),
//		viewModel = PokemonListViewModel
	)
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPrev() {
	var text by remember {
		mutableStateOf("")
	}
	Column() {
		SearchBar(search = text, onSearch = { text = it })
		Text(text = text)
	}

}