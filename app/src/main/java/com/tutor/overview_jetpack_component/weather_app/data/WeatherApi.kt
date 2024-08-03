package com.tutor.overview_jetpack_component.weather_app.data

import com.tutor.overview_jetpack_component.weather_app.data.model.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

	@GET("/v1/current.json")
	suspend fun getWeather(
		@Query("key") apiKey: String,
		@Query("q") city: String
	): Response<WeatherDto>

}