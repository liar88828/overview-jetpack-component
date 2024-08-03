package com.tutor.overview_jetpack_component.weather_app.data

import com.tutor.overview_jetpack_component.weather_app.utils.Constance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherInstance {
	private fun getInstance(): Retrofit {
		return Retrofit.Builder()
			.baseUrl(Constance.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}

	val weatherApi: WeatherApi = getInstance().create(WeatherApi::class.java)

}