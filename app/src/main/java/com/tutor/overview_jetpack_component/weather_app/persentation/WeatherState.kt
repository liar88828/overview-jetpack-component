package com.tutor.overview_jetpack_component.weather_app.persentation

import com.tutor.overview_jetpack_component.weather_app.data.model.WeatherDto

data class WeatherState(
	val weatherData: List<WeatherDto> = emptyList(),
	val loading: Boolean = false,
	val error: String? = null,
	val city: String = ""
)
//3f4a22cb831d49bea32145113240308