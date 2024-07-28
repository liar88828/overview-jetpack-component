package com.tutor.overview_jetpack_component.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNavigationDrawer(modifier: Modifier = Modifier) {
	val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
	val scope = rememberCoroutineScope()


	ModalNavigationDrawer(drawerState = drawerState, drawerContent = { Sidebar() }, content = {
		Scaffold(topBar = {
			TopAppBar(navigationIcon = {
				IconButton(onClick = { scope.launch { drawerState.open() } }) {
					Icon(
						imageVector = Icons.Default.Menu,
						contentDescription = "Icons Menu"
					)
				}
			}, title = {
				Text(text = "Top AppBar")
			})
		}) { paddingValues ->
			Box(
				modifier = modifier
					.fillMaxSize()
					.padding(paddingValues),
				contentAlignment = Alignment.Center
			) {
				Text(
					text = "Home", fontSize = 32.sp
				)
			}
		}
	})
}

data class ItemSidebar(
	val title: String,
	val icon: ImageVector,
)

val itemSidebar = listOf(
	ItemSidebar("Profile", Icons.Default.AccountBox),
	ItemSidebar("Email", Icons.Default.Email),
	ItemSidebar("More", Icons.Default.MoreVert),
)

@Composable
private fun Sidebar() {
	ModalDrawerSheet {
		itemSidebar.forEach {
			NavigationDrawerItem(label = { Text(text = it.title) }, icon = {
				Icon(
					imageVector = it.icon, contentDescription = it.title
				)
			}, selected = false, onClick = { /*TODO*/ })
		}
	}
}

@Preview
@Composable
private fun SidebarPrev() {
	OverviewJetpackComponentTheme {
		Sidebar()
	}
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MyNavigationDrawerPrev() {
	OverviewJetpackComponentTheme {
		MyNavigationDrawer()
	}
}