package com.tutor.overview_jetpack_component.movieApp.data.local.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {
	@Upsert
	suspend fun upsertAll(movies: List<MovieEntity>)

	@Query("SELECT * FROM movieentity WHERE id = :id")
	suspend fun getMovieById(id: Int): MovieEntity

	@Query("SELECT * FROM movieentity WHERE category = :category")
	suspend fun getMoviesByCategory(category: String): List<MovieEntity>

	@Query("SELECT * FROM movieentity")
	suspend fun getAllMovies(): List<MovieEntity>

}