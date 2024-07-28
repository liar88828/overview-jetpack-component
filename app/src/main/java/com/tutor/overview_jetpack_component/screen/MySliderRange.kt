package com.tutor.overview_jetpack_component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme
import kotlin.math.roundToInt

@Composable
fun MySliderRange(modifier: Modifier = Modifier) {
	Surface(modifier = modifier.fillMaxSize()) {
		Column(
			modifier = modifier
				.fillMaxSize()
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			var sliderPosition by remember { mutableFloatStateOf(0f) }

			Slider(
				colors = SliderDefaults.colors(
					thumbColor = MaterialTheme.colorScheme.secondary,
					activeTrackColor = MaterialTheme.colorScheme.primary,
					inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
				),
				steps = 5,
				valueRange = 0f..50f,
				value = sliderPosition,
				onValueChange = { sliderPosition = it },
			)
			Text(
				text = sliderPosition.roundToInt().toString(),
				fontSize = 32.sp
			)
		}
	}
}

@Composable
fun MySliderRange2(modifier: Modifier = Modifier) {
	Surface(modifier = modifier.fillMaxSize()) {
		Column(
			modifier = modifier
				.fillMaxSize()
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			var sliderRangePosition by remember { mutableStateOf(0f..100f) }
			val startRange by remember {
				derivedStateOf {
					sliderRangePosition.start.roundToInt().toString()
				}
			}
			val endRange by remember {
				derivedStateOf {
					sliderRangePosition.endInclusive.roundToInt().toString()
				}
			}


			RangeSlider(
				value = sliderRangePosition,
				onValueChange = { sliderRangePosition = it },
				steps = 10,
				valueRange = 0f..100f,
				onValueChangeFinished = {
				}
			)

			Text(
				text = "$startRange - $endRange",
				fontSize = 32.sp
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun MySliderRangePrev() {
	OverviewJetpackComponentTheme {
//		MySliderRange()
		MySliderRange2()
	}
}