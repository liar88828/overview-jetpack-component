package com.tutor.overview_jetpack_component.movieApp.di

import com.tutor.overview_jetpack_component.movieApp.data.repository.MovieListRepositoryImpl
import com.tutor.overview_jetpack_component.movieApp.domain.repository.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

	@Binds
	@Singleton
	abstract fun bindMovieRepository(movieListRepositoryImpl: MovieListRepositoryImpl): MovieListRepository
}