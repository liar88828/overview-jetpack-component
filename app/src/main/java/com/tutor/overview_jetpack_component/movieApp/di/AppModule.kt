package com.tutor.overview_jetpack_component.movieApp.di

import android.app.Application
import androidx.room.Room
import com.tutor.overview_jetpack_component.movieApp.data.local.movie.MovieDatabase
import com.tutor.overview_jetpack_component.movieApp.data.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieModule {
	private val interceptor = HttpLoggingInterceptor().apply {
		level = HttpLoggingInterceptor.Level.BODY
	}
	private val client: OkHttpClient = OkHttpClient.Builder()
		.addInterceptor(interceptor)
		.build()

	@Singleton
	@Provides
	fun providerMovieApp(): MovieApi {
		return Retrofit.Builder()
			.baseUrl("https://api.themoviedb.org/3/movie/")
			.addConverterFactory(GsonConverterFactory.create())
			.client(client)
			.build()
			.create(MovieApi::class.java)
	}

	@Provides
	@Singleton
	fun provideMovieDatabase(
		application: Application
	): MovieDatabase {
		return Room.databaseBuilder(
			application,
			MovieDatabase::class.java,
			"movie.db"
		)
			.build()

	}
}