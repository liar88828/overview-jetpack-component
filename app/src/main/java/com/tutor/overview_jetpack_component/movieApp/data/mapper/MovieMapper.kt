package com.tutor.overview_jetpack_component.movieApp.data.mapper

import com.tutor.overview_jetpack_component.movieApp.data.local.movie.MovieEntity
import com.tutor.overview_jetpack_component.movieApp.data.remote.respnse.MovieDto
import com.tutor.overview_jetpack_component.movieApp.domain.model.Movie

fun MovieDto.toMovieEntity(
	category: String
): MovieEntity {
	return MovieEntity(

		adult = adult ?: false,
		backdrop_path = backdrop_path ?: "",
		original_title = original_title ?: "",
		overview = overview ?: "",
		poster_path = poster_path ?: "",
		release_date = release_date ?: "",
		title = title ?: "",
		vote_average = vote_average ?: 0.0,
		vote_count = vote_count ?: 0,
		popularity = popularity ?: 0.0,
		original_language = original_language ?: "",
		video = video ?: false,
		category = category,

		genre_ids = try {
			genre_ids?.joinToString(",") ?: "-1,-2"
		} catch (e: Exception) {
			"-1,-2"
		},
		id = id ?: -1
	)
}

fun MovieEntity.toMovie(
	category: String,
): Movie {
	return Movie(backdrop_path = backdrop_path,
		original_title = original_title,
		overview = overview,
		poster_path = poster_path,
		release_date = release_date,
		title = title,
		vote_average = vote_average,
		vote_count = vote_count,
		popularity = popularity,
		original_language = original_language,
		video = video,
		adult = adult,
		id = id,
		category = category,
		genre_ids = try {
			genre_ids.split(",")
				.map { it.toInt() }
		} catch (e: Exception) {
			listOf(-1, -2)
		})

}