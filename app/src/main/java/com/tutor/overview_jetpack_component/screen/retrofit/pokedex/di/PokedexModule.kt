package com.tutor.overview_jetpack_component.screen.retrofit.pokedex.di

import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.data.PokeApi
import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.repository.PokemonRepository
import com.tutor.overview_jetpack_component.screen.retrofit.pokedex.utils.PokeConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokedexModule {

	@Singleton
	@Provides
	fun providerPokemonRepository(api: PokeApi) = PokemonRepository(api)

	@Singleton
	@Provides
	fun providerPokeApi(): PokeApi {
		return Retrofit.Builder()
			.baseUrl(PokeConstants.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(PokeApi::class.java)

	}
}
