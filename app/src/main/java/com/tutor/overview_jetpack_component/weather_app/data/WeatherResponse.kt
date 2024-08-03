package com.tutor.overview_jetpack_component.weather_app.data

sealed class WeatherResponse<out T> {
	data class Success<out T>(val data: T?) : WeatherResponse<T>()
	data class Error(val message: String) : WeatherResponse<Nothing>()
	data object Loading : WeatherResponse<Nothing>()
}