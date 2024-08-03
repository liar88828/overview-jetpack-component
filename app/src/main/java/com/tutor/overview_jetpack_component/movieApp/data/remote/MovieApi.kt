package com.tutor.overview_jetpack_component.movieApp.data.remote

import com.tutor.overview_jetpack_component.movieApp.data.remote.respnse.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
	@GET("movie/{category}")
	suspend fun getMovieList(
		@Path("category") category: String,
		@Query("page") page: Int,
		@Query("api_key") apiKey: String = API_KEY
	): MovieListDto

	companion object {
		//		'https://api.themoviedb.org/3/';
		const val BASE_URL = "https://api.themoviedb.org/3/movie/"
		const val KEY = "b51a21c0d928f23dc7911b6c7a81896a"
		const val TOKEN =
			"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNTFhMjFjMGQ5MjhmMjNkYzc5MTFiNmM3YTgxODk2YSIsIm5iZiI6MTcyMjY4MDc2My44MzUyNTYsInN1YiI6IjY2YWUwMmUyYzA0ZjNhYTUzZWRjYmVkMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.e1pFwIbOG0FUdmweh7mWeIcwT6e_jc4I9Hjgmlfv7hg"
		const val API_KEY = "api_key"
		const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
	}
}