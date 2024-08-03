package com.tutor.overview_jetpack_component.movieApp.persentation

sealed interface MovieListEvent {
	data class Paginate(val category: String) : MovieListEvent
	data object Navigate : MovieListEvent
}