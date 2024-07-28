package com.tutor.overview_jetpack_component.screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme

@Composable
fun MyCard(modifier: Modifier = Modifier) {
	Surface(modifier = modifier.fillMaxSize()) {
		Column(
			modifier = modifier.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.SpaceEvenly
		) {
			Card(
				shape = RectangleShape,
				colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
				elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
				border = BorderStroke(2.dp, Color.Blue),
			) {
				Text(
					modifier = modifier.padding(8.dp),
					text = "Simple Text",
					fontSize = 26.sp
				)
			}
			ElevatedCard(
			) {
				Text(
					text = "Elevated Card",
					fontSize = 26.sp,
					modifier = modifier.padding(8.dp)
				)
			}
			OutlinedCard(
				modifier = modifier
					.fillMaxWidth()
					.padding(horizontal = 8.dp)
			) {
				Text(
					text = "Outlined Card",
					fontSize = 26.sp,
					modifier = modifier.padding(8.dp)
				)
			}
		}
	}

}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MyCardPrev() {
	OverviewJetpackComponentTheme {
		MyCard()
	}
}