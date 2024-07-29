package com.tutor.overview_jetpack_component.screen.notification_screen

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun MyNotification(context: Context) {
	val notification = NotificationService(context)
	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		Column(
			modifier = Modifier.fillMaxWidth(),
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
				val permissionStateException = rememberPermissionState(
					permission = Manifest.permission.POST_NOTIFICATIONS
				)
				if (!permissionStateException.status.isGranted) {
					OutlinedButton(onClick = { permissionStateException.launchPermissionRequest() }) {
						Text(
							text = "Allow Notification",
							fontSize = 22.sp
						)
					}
				}
			}
			Spacer(modifier = Modifier.size(16.dp))
			Button(onClick = { notification.showNotification() }) {
				Text(
					text = "Show Notification",
					fontSize = 22.sp
				)
			}
		}
	}
}
