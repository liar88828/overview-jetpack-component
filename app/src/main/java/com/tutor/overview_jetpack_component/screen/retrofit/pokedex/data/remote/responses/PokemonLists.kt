package com.tutor.overview_jetpack_component.screen.retrofit.pokedex.data.remote.responses

data class PokemonLists(
	val count: Int,
	val next: String,
	val previous: Any,
	val results: List<Result>
)