package com.tutor.overview_jetpack_component.screen.retrofit.pokedex.data.remote.responses

data class Move(
	val move: MoveX,
	val version_group_details: List<VersionGroupDetail>
)