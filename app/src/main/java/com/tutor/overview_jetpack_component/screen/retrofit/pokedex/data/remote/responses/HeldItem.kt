package com.tutor.overview_jetpack_component.screen.retrofit.pokedex.data.remote.responses

data class HeldItem(
	val item: Item,
	val version_details: List<VersionDetail>
)