package com.tutor.overview_jetpack_component.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme
import kotlinx.coroutines.delay

@Composable
fun MyIndicatorScreen(modifier: Modifier = Modifier) {
	var isLoading by remember { mutableStateOf(true) }

	LaunchedEffect(
		key1 = true,
		block = {
			delay(4000)
			isLoading = false
		})


	AnimatedContent(
		targetState = isLoading,
		label = "animated_content",
		transitionSpec = {
			fadeIn(
				animationSpec = tween(500, easing = LinearEasing)
			) togetherWith fadeOut(
				animationSpec = tween(500, easing = LinearEasing)
			)
		}
	) { loading ->
		if (loading) {
			LoadingScreen()
		} else {
			HomeScreen()
		}
	}
}

@Composable
private fun HomeScreen(modifier: Modifier = Modifier) {
	Box(
		modifier = modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		Text(
			text = "Home Screen",
			fontSize = 32.sp,
			fontWeight = FontWeight.SemiBold
		)
	}
}

@Composable
fun MyIndicatorScreen2(modifier: Modifier = Modifier) {
	var progress by remember { mutableFloatStateOf(0f) }
	Scaffold(floatingActionButton = {
		ExtendedFloatingActionButton(
			onClick = { progress++ },
			text = { Text(text = "Progress") },
			icon = { Icon(imageVector = Icons.Default.Edit, contentDescription = "Add Icons") },
		)
	})
	{
		Column(
			modifier
				.fillMaxSize()
				.padding(it),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			CircularProgressIndicator(
				progress = { progress / 10 },
				modifier = modifier.size(64.dp),
				color = MaterialTheme.colorScheme.secondary,
				strokeWidth = 4.dp,
				trackColor = MaterialTheme.colorScheme.surfaceVariant,
				strokeCap = StrokeCap.Round,
			)
			Spacer(modifier = modifier.size(64.dp))
			LinearProgressIndicator(
				progress = { progress / 10 },
				modifier = modifier.fillMaxWidth(),
				color = MaterialTheme.colorScheme.secondary,
				trackColor = MaterialTheme.colorScheme.surfaceVariant,
				strokeCap = StrokeCap.Round
			)
		}
	}

}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
	Box(
		modifier = modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		Column(
			modifier.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.SpaceEvenly
		) {
			LinearProgressIndicator(
				modifier = modifier.fillMaxWidth(),
				color = MaterialTheme.colorScheme.secondary,
				strokeCap = StrokeCap.Round
			)
//			CircularProgressIndicator(
//				modifier = modifier.size(64.dp),
//				color = MaterialTheme.colorScheme.secondary,
//				trackColor = MaterialTheme.colorScheme.surfaceVariant,
//				strokeCap = StrokeCap.Round,
//				strokeWidth = 4.dp
//			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun MyIndicatorPrev() {
	OverviewJetpackComponentTheme {
//		MyIndicatorScreen()
		MyIndicatorScreen2()
	}
}