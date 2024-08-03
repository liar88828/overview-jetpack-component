package com.tutor.overview_jetpack_component.weather_app.persentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutor.overview_jetpack_component.weather_app.data.WeatherInstance
import com.tutor.overview_jetpack_component.weather_app.data.WeatherResponse
import com.tutor.overview_jetpack_component.weather_app.data.model.WeatherDto
import com.tutor.overview_jetpack_component.weather_app.utils.Constance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class WeatherViewModel : ViewModel() {
	private val weatherApi = WeatherInstance.weatherApi

	private val _weatherResponse = MutableLiveData<WeatherResponse<WeatherDto>>()
	val weatherResponse: MutableLiveData<WeatherResponse<WeatherDto>> = _weatherResponse

	private val _weatherState = MutableStateFlow(WeatherState())
	val weatherState = _weatherState.asStateFlow()

	fun onEvent(event: WeatherEvent) {
		when (event) {
			is WeatherEvent.GetCity -> {
				_weatherResponse.value = WeatherResponse.Loading

				viewModelScope.launch {
					val response = try {
						weatherApi.getWeather(city = event.city, apiKey = Constance.KEY)
					} catch (e: Exception) {
						_weatherResponse.value = WeatherResponse.Error("Something went wrong")
						return@launch
					} catch (e: HttpException) {
						_weatherResponse.value = WeatherResponse.Error(e.message.toString())
						return@launch
					}
					if (response.isSuccessful) {
						response.body()
							?.let {
								_weatherResponse.value = WeatherResponse.Success(it)
							}
					}

				}

			}

			is WeatherEvent.GetNameCity -> {
				_weatherState.update {
					it.copy(
						city = event.nameCity
					)
				}
			}
		}
	}
}


