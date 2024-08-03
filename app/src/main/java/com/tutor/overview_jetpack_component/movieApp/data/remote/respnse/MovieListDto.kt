package com.tutor.overview_jetpack_component.movieApp.data.remote.respnse

data class MovieListDto(
	val page: Int,
	val results: List<MovieDto>,
	val total_pages: Int,
	val total_results: Int
)