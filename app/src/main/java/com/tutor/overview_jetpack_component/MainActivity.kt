package com.tutor.overview_jetpack_component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tutor.overview_jetpack_component.screen.notification_screen.MyNotification
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			OverviewJetpackComponentTheme {
				MyNotification(applicationContext)
			}
		}
	}
//	@Composable
//	@OptIn(ExperimentalPermissionsApi::class)
//	private fun MyNotification() {
//		val notification = NotificationService(applicationContext)
//		Box(
//			modifier = Modifier.fillMaxSize(),
//			contentAlignment = Alignment.Center
//		) {
//			Column(
//				modifier = Modifier.fillMaxWidth(),
//				horizontalAlignment = Alignment.CenterHorizontally,
//			) {
//				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//					val permissionStateException = rememberPermissionState(
//						permission = Manifest.permission.POST_NOTIFICATIONS
//					)
//					if (!permissionStateException.status.isGranted) {
//						OutlinedButton(onClick = { permissionStateException.launchPermissionRequest() }) {
//							Text(
//								text = "Allow Notification",
//								fontSize = 22.sp
//							)
//						}
//					}
//				}
//				Spacer(modifier = Modifier.size(16.dp))
//				Button(onClick = { notification.showNotification() }) {
//					Text(
//						text = "Show Notification",
//						fontSize = 22.sp
//					)
//				}
//			}
//		}
//	}
}

