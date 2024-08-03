package com.tutor.overview_jetpack_component.movieApp.data.repository

import com.tutor.overview_jetpack_component.movieApp.data.local.movie.MovieDatabase
import com.tutor.overview_jetpack_component.movieApp.data.mapper.toMovie
import com.tutor.overview_jetpack_component.movieApp.data.mapper.toMovieEntity
import com.tutor.overview_jetpack_component.movieApp.data.remote.MovieApi
import com.tutor.overview_jetpack_component.movieApp.domain.model.Movie
import com.tutor.overview_jetpack_component.movieApp.domain.repository.MovieListRepository
import com.tutor.overview_jetpack_component.movieApp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class MovieListRepositoryImpl
@Inject constructor(
	private val api: MovieApi,
	private val db: MovieDatabase
) : MovieListRepository {
	override suspend fun getMovieList(
		forceFetchFromRemote: Boolean,
		category: String,
		page: Int
	): Flow<Resource<List<Movie>>> {
		return flow {
			emit(Resource.Loading(true))
			val localMovieList = db.dao.getMoviesByCategory(category)
			val shouldJustLoadLocal = localMovieList.isNotEmpty() && !forceFetchFromRemote
			if (shouldJustLoadLocal) {
				emit(Resource.Success(
					data = localMovieList.map {
						it.toMovie(category = category)
					}
				))
				emit(Resource.Loading(false))
				return@flow
			}
			val movieListFromApi = try {
				api.getMovieList(category, page)
			} catch (e: IOException) {
				e.printStackTrace()
				emit(Resource.Error("Internet connection error"))
				return@flow
			} catch (e: Exception) {
				e.printStackTrace()
				emit(Resource.Error("Couldn't load data"))
				return@flow

			} catch (e: HttpException) {
				e.printStackTrace()
				emit(Resource.Error("Server is down"))
				return@flow

			}

			val movieEntities = movieListFromApi.results.let {
				it.map { movieDto ->
					movieDto.toMovieEntity(category)
				}
			}
			db.dao.upsertAll(movieEntities)
			emit(Resource.Success(
				data = movieEntities.map {
					it.toMovie(category = category)
				}
			))
			emit(Resource.Loading(false))

		}
	}

	override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {
		return flow {
			emit(Resource.Loading(true))

			val movieEntity = db.dao.getMovieById(id)
			if (movieEntity != null) {
				emit(Resource.Success(movieEntity.toMovie(movieEntity.category)))
				emit(Resource.Loading(false))
				return@flow
			}
			emit(Resource.Error("Movie not found"))
			emit(Resource.Loading(false))

		}
	}
}