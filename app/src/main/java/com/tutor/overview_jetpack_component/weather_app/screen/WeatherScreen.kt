package com.tutor.overview_jetpack_component.weather_app.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tutor.overview_jetpack_component.weather_app.data.WeatherResponse
import com.tutor.overview_jetpack_component.weather_app.data.model.Location
import com.tutor.overview_jetpack_component.weather_app.data.model.WeatherDto
import com.tutor.overview_jetpack_component.weather_app.persentation.WeatherEvent
import com.tutor.overview_jetpack_component.weather_app.persentation.WeatherState
import com.tutor.overview_jetpack_component.weather_app.persentation.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
//	weatherResult: State<WeatherResponse<WeatherDto>?>,
	state: State<WeatherState>,
	onEvent: (WeatherEvent) -> Unit,
	viewModel: WeatherViewModel,
	modifier: Modifier = Modifier,

	) {
	val weatherResult = viewModel.weatherResponse.observeAsState()
	val city = remember { mutableStateOf("") }

	Scaffold(

		modifier = modifier.fillMaxSize(),
		bottomBar = { MyNavigationBar(modifier) },
		topBar = { MyTopBar() },
		floatingActionButton = { MyFloatingActionButton() }) { paddingValues ->
		Column(
			modifier = modifier
				.padding(paddingValues)
				.fillMaxWidth()
				.padding(20.dp)
		) {
			SearchWeather(modifier, city, onEvent, state)

			when (val result = weatherResult.value) {
				is WeatherResponse.Error -> Text(text = result.message)
				WeatherResponse.Loading -> CircularProgressIndicator()

				is WeatherResponse.Success -> {
					result.data?.let {
						WeatherDetail(
							item = it,
						)
					}
				}

				null -> {}
			}

		}
	}

}

@Composable
private fun MyFloatingActionButton() {
	FloatingActionButton(onClick = { /*TODO*/ }) {
		Icon(
			imageVector = Icons.Default.Add, contentDescription = "Add Icon"
		)

	}
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MyTopBar() {
	CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
		containerColor = MaterialTheme.colorScheme.primaryContainer,

		), title = {
		Text(text = "Weather App")
	},

		navigationIcon = {
			IconButton(onClick = { /*TODO*/ }) {
				Icon(
					imageVector = Icons.Default.Search, contentDescription = "Search Icon"
				)
			}
		})
}

@Composable
private fun MyNavigationBar(modifier: Modifier) {
	NavigationBar() {
		NavigationBarItem(onClick = {}, selected = false, label = { Text(text = "Home") }, icon = {
			Icon(
				imageVector = Icons.Default.Home, contentDescription = "Home Icon"
			)
		})
		FloatingActionButton(modifier = modifier.padding(bottom = 8.dp), onClick = { /*TODO*/ }) {
			Icon(
				imageVector = Icons.Default.Public, contentDescription = "Public Icon"
			)
		}
		NavigationBarItem(onClick = {},
			selected = false,
			label = { Text(text = "Setting") },
			icon = {
				Icon(
					imageVector = Icons.Default.Settings, contentDescription = "setting Icon"
				)
			})
	}
}

@Composable
private fun SearchWeather(
	modifier: Modifier,
	city: MutableState<String>,
	onEvent: (WeatherEvent) -> Unit,
	state: State<WeatherState>
) {
	OutlinedTextField(modifier = modifier.fillMaxWidth(),
		value = state.value.city,
		onValueChange = {
			onEvent(WeatherEvent.GetNameCity(it))
		},
		label = { Text(text = "City") },
		leadingIcon = {
			Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location Icon")
		},
		trailingIcon = {
			IconButton({
				onEvent(WeatherEvent.GetCity(state.value.city))
			}) {
				Icon(
					imageVector = Icons.Default.Search, contentDescription = "Search Icon"
				)
			}
		}

	)
}

@Composable
fun WeatherDetail(
	item: WeatherDto,
	modifier: Modifier = Modifier
) {

	Card(
		modifier = modifier
			.fillMaxWidth()
			.padding(20.dp),

		) {
		Row(
			modifier = modifier
				.fillMaxWidth()
				.padding(18.dp),
			horizontalArrangement = Arrangement.spacedBy(10.dp),
			verticalAlignment = Alignment.CenterVertically

		) {
			Icon(
				imageVector = Icons.Default.LocationOn,
				contentDescription = "Location Icon",
				modifier = modifier.size(40.dp),
				tint = MaterialTheme.colorScheme.primary
			)
			Text(
				text = item.location.country,
				fontSize = 32.sp,
				color = MaterialTheme.colorScheme.primary
			)
		}
		Column(
			modifier = modifier
				.fillMaxWidth()
				.padding(12.dp),
			verticalArrangement = Arrangement.spacedBy(6.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = " ${item.current?.temp_c} ° C", fontSize = 64.sp,
				fontWeight = FontWeight.Bold,
				color = MaterialTheme.colorScheme.primary
			)
			AsyncImage(
				model = "https:${item.current?.condition?.icon}".replace("64x64", "128x128"),
				contentDescription = "Weather Icon",
				modifier = modifier.size(140.dp)
			)
//			Icon(
//				imageVector = Icons.Default.Cloud,
//				contentDescription = "Cloud Icon",
//				modifier = modifier.size(140.dp),
//				tint = MaterialTheme.colorScheme.primary
//			)
			item.current?.condition?.text?.let {
				Text(
					text = it,
					fontSize = 32.sp, color = MaterialTheme.colorScheme.primary
				)
			}
		}
		Card(
			modifier
				.fillMaxWidth()
				.verticalScroll(rememberScrollState()),
			colors = CardDefaults.cardColors(
				containerColor = MaterialTheme.colorScheme.primary.copy(0.5f)
			)
		) {
			Row(
				modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceAround
			) {
				WeatherCardItem("Humidity", "${item.current?.humidity}%")
				WeatherCardItem("Wind Speed", "${item.current?.wind_kph} km/h")
			}
			Row(
				modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceAround
			) {
				WeatherCardItem("UV", "${item.current?.uv}%")
				WeatherCardItem("Participation", "${item.current?.precip_mm} mm")
			}
			Row(
				modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceAround
			) {
				WeatherCardItem("Local Time", item.location.localtime.split(" ")[0])
				WeatherCardItem("Local Date", item.location.localtime.split(" ")[1])
			}
		}
	}
}

@Composable
fun WeatherCardItem(
	title: String,
	value: String,
	modifier: Modifier = Modifier
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier.padding(24.dp)

	) {
		Text(value, fontWeight = FontWeight.Bold, fontSize = 18.sp)
		Text(title)
	}
}

@Preview(showBackground = true)
@Composable
private fun WeatherDetailPrev() {
	WeatherDetail(
		item =
		WeatherDto(

			location = Location(
				country = "Thailand",
				name = "Chiang Mai",
				region = "Chiang Mai",
				lat = 18.7969,
				lon = 98.982,
				tz_id = "Asia/Bangkok",
				localtime = "20:20",
				localtime_epoch = 23
			),
			current = null
		)
	)
}
//
//@Preview
//@Composable
//private fun WeatherScreenPrev() {
//	WeatherScreen(
//		weatherResult = remember {
//			mutableStateOf(WeatherResponse.Loading)
//		},
//		state = remember {
//			mutableStateOf(WeatherState())
//		},
//		onEvent = {}
//
//	)
//}