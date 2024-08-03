package com.tutor.overview_jetpack_component.movieApp.persentation

import com.tutor.overview_jetpack_component.movieApp.domain.model.Movie

data class MovieListState(
	val isLoading: Boolean = false,
//
	val populationMovieListPage: Int = 1,
	val upComingMovieListPage: Int = 1,
//
	val isCurrentPopularScreen: Boolean = true,
//
	val popularMovieList: List<Movie> = emptyList(),
	val upcomingMovieList: List<Movie> = emptyList(),

	)