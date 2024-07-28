package com.tutor.overview_jetpack_component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tutor.overview_jetpack_component.screen.MyScaffold
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			OverviewJetpackComponentTheme {
				MyScaffold(name = "Android")
			}
		}
	}

}

