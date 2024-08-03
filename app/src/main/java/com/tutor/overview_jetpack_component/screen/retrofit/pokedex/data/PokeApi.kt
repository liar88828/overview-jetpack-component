package com.tutor.overview_jetpack_component.screen.retrofit.pokedex.data

import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.data.remote.responses.Pokemon
import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.data.remote.responses.PokemonLists
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
	@GET("pokemon")
	suspend fun getPokemonList(
		@Query("limit") limit: Int,
		@Query("offset") offset: Int,
	): PokemonLists

	@GET("pokemon/{name}")
	suspend fun getPokemonInfo(
		@Path("name") name: String
	): Pokemon

}