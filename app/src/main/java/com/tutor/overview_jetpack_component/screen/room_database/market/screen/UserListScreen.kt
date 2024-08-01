package com.tutor.overview_jetpack_component.screen.room_database.market.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tutor.overview_jetpack_component.screen.room_database.market.data.User
import com.tutor.overview_jetpack_component.screen.room_database.market.persentation.UserEvent
import com.tutor.overview_jetpack_component.screen.room_database.market.persentation.UserRoute
import com.tutor.overview_jetpack_component.screen.room_database.market.persentation.UserState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
	state: UserState,
	onEvent: (UserEvent) -> Unit,
	navController: NavHostController,
	modifier: Modifier = Modifier
) {
	Scaffold(
		bottomBar = {
			NavigationBar {
				NavigationBarItem(
					selected = false,
					onClick = {},
					icon = {
						Icon(
							imageVector = Icons.Default.Person,
							contentDescription = "Person Icon"
						)
					})

				NavigationBarItem(
					selected = false,
					onClick = {},
					icon = {
						Icon(
							imageVector = Icons.Default.ShoppingCart,
							contentDescription = "Shopping Icon"
						)
					})
				FloatingActionButton({}) {
					Icon(
						imageVector = Icons.Default.Search,
						contentDescription = "Search Icon"
					)
				}
				NavigationBarItem(
					selected = false,
					onClick = {},
					icon = {
						Icon(
							imageVector = Icons.Default.Email,
							contentDescription = "Email Icon"
						)
					})

				NavigationBarItem(
					selected = false,
					onClick = {},
					icon = {
						Icon(
							imageVector = Icons.Default.Settings,
							contentDescription = "Settings Icon"
						)
					})
			}
		},
		floatingActionButton = {
			FloatingActionButton({
				navController.navigate(UserRoute.Add.route)
			}) {
				Icon(
					imageVector = Icons.Default.Add,
					contentDescription = "Add Icon"
				)
			}
		},
		topBar = {
			CenterAlignedTopAppBar(
				title = { Text(text = "User List") },
				navigationIcon = {
					IconButton({}) {
						Icon(Icons.Default.Menu, contentDescription = "Menu Icon")
					}
				},
				actions = {
					IconButton({}) {
						Icon(
							Icons.Default.MoreVert,
							contentDescription = "List Icon"
						)
					}
				},
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = MaterialTheme.colorScheme.primaryContainer,
				)
			)
		},
		modifier = modifier.fillMaxSize()
	) { paddingValues ->
		Box(
			modifier = modifier
				.fillMaxWidth()
				.padding(paddingValues)
		) {
			if (state.users.isEmpty()) {
				Text("Item is Empty")
			} else {
				LazyColumn(
					modifier = modifier.fillMaxWidth(),
					contentPadding = PaddingValues(16.dp),
					verticalArrangement = Arrangement.spacedBy(16.dp)
				) {
					items(state.users, key = { user -> user.id }) { user ->
						Card(modifier.fillMaxWidth()) {
							Row(
								modifier = modifier
									.fillMaxWidth()
									.padding(vertical = 8.dp, horizontal = 10.dp),
								horizontalArrangement = Arrangement.SpaceBetween
							) {
								Column(
									modifier = modifier.padding(8.dp),
									verticalArrangement = Arrangement.spacedBy(8.dp)
								) {
									Text(
										text = user.name,
										modifier = Modifier
											.size(width = 200.dp, height = 20.dp),
										maxLines = 1,
										fontSize = 18.sp,
										fontWeight = FontWeight.SemiBold,
										overflow = TextOverflow.Ellipsis,
									)
									Text(
										text = user.age,
										fontSize = 14.sp,
										fontWeight = FontWeight.Medium,
									)
								}
								FloatingActionButton(onClick = { onEvent(UserEvent.DeleteUser(user)) }) {
									Icon(
										imageVector = Icons.Default.Delete,
										contentDescription = "Delete Icon"
									)
								}
							}
						}
					}
				}
			}
		}
	}

}

@Preview
@Composable
private fun UserListScreenPrev() {
	UserListScreen(
		state = UserState(
			users = listOf(
				User(
					age = "23",
					name = "Michael Usman",
					id = 23
				),
				User(
					age = "12",
					name = "Michael Usman Doris Biswas",
					id = 243
				)
			)
		),
		onEvent = {},
		navController = NavHostController(LocalContext.current)
	)

}