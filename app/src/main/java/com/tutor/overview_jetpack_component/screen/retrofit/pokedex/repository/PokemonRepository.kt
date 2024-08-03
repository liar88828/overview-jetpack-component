package com.tutor.overview_jetpack_component.screen.retrofit.pokedex.repository

import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.data.PokeApi
import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.data.remote.responses.Pokemon
import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.data.remote.responses.PokemonLists
import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.HttpException
import javax.inject.Inject

@ActivityScoped
class PokemonRepository
@Inject
constructor(
	private val api: PokeApi
) {
	suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonLists> {

		val response = try {
			api.getPokemonList(limit, offset)
		} catch (e: Exception) {
			return Resource.Error("An Unknown Error Occurred")
		} catch (e: HttpException) {
			return Resource.Error("An Unknown Error Server")
		}
		return Resource.Success(response)
	}

	suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
		val response = try {
			api.getPokemonInfo(pokemonName)
		} catch (e: Exception) {
			return Resource.Error("An Unknown Error Occurred")
		} catch (e: HttpException) {
			return Resource.Error("An Unknown Error Server")
		}
		return Resource.Success(response)
	}

}