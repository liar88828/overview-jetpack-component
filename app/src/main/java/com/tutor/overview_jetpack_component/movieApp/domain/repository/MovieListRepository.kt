package com.tutor.overview_jetpack_component.movieApp.domain.repository

import com.tutor.overview_jetpack_component.movieApp.domain.model.Movie
import com.tutor.overview_jetpack_component.movieApp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
	suspend fun getMovieList(
		forceFetchFromRemote: Boolean,
		category: String,
		page: Int,
	): Flow<Resource<List<Movie>>>

	suspend fun getMovie(id: Int): Flow<Resource<Movie>>

}