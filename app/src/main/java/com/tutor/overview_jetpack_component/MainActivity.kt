package com.tutor.overview_jetpack_component

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.tutor.overview_jetpack_component.screen.preferences_datastore.MyMainModel
import com.tutor.overview_jetpack_component.screen.preferences_datastore.MyPreferenceDataBaseScreen
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	private val mainViewModel by viewModels<MyMainModel>()

	@SuppressLint("RememberReturnType")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			OverviewJetpackComponentTheme {
//				MyNotification(applicationContext)
				MyPreferenceDataBaseScreen(mainViewModel)
			}
		}
	}

}

