package com.tutor.overview_jetpack_component.screen.retrofit.pokedex.persentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.data.models.PokeListEntry
import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.repository.PokemonRepository
import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.utils.PokeConstants.PAGE_SIZE
import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel
@Inject constructor(
	private val repository: PokemonRepository
) : ViewModel() {

	private var curPage = 0

	var pokemonList = mutableStateOf<List<PokeListEntry>>(listOf())
	var loadError = mutableStateOf("")
	var isLoading = mutableStateOf(false)
	var endReached = mutableStateOf(false)

	fun loadPokemonPaginated() {
		viewModelScope.launch {
			isLoading.value = true
			val result = repository.getPokemonList(
				PAGE_SIZE, curPage * PAGE_SIZE
			)
			when (result) {
				is Resource.Error -> {

					loadError.value = result.message!!
					isLoading.value = false
				}

				is Resource.Success -> {
					endReached.value = curPage * PAGE_SIZE >= result.data!!.count
					val pokedexEntry = result.data.results.mapIndexed { index, entry ->
						val number = if (entry.url.endsWith("/")) {
							entry.url.dropLast(1)
								.takeLastWhile { it.isDigit() }
						} else {
							entry.url.dropLast(1)
								.takeLastWhile { it.isDigit() }
						}
						var url =
							"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png"
						PokeListEntry(entry.name.capitalized(), url, number.toInt())
					}
					curPage++
					loadError.value = ""
					isLoading.value = false
					pokemonList.value += pokedexEntry
				}
			}
//				is Resource.Loading -> TODO()
		}
	}

}

fun String.capitalized(): String {
	return replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}