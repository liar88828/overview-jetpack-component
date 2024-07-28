package com.tutor.overview_jetpack_component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			OverviewJetpackComponentTheme {
				Greeting(name = "Android")
			}
		}
	}

}

@Composable
private fun Greeting(name: String) {
	Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	OverviewJetpackComponentTheme {
		Greeting("Android")
	}
}