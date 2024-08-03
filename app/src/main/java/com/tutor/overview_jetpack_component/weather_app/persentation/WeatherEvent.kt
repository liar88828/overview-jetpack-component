package com.tutor.overview_jetpack_component.weather_app.persentation

sealed interface WeatherEvent {
	data class GetCity(var city: String) : WeatherEvent
	data class GetNameCity(var nameCity: String) : WeatherEvent
}
