package com.tutor.overview_jetpack_component.screen.room_database.product.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tutor.overview_jetpack_component.R
import com.tutor.overview_jetpack_component.screen.room_database.product.data.Product
import com.tutor.overview_jetpack_component.screen.room_database.product.data.productExample
import com.tutor.overview_jetpack_component.screen.room_database.product.persentation.ProductEvent
import com.tutor.overview_jetpack_component.screen.room_database.product.persentation.ProductState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
	navController: NavHostController,
	state: ProductState,
	onEvent: (ProductEvent) -> Unit,
	modifier: Modifier = Modifier
) {
	Scaffold(
		modifier = modifier
			.fillMaxSize(),
//		containerColor=MaterialTheme.colorScheme.errorContainer,
//		contentColor = MaterialTheme.colorScheme.onErrorContainer,
		floatingActionButton = {
			FloatingActionButton(
				onClick = { },
//				containerColor = MaterialTheme.colorScheme.errorContainer,
//				contentColor = MaterialTheme.colorScheme.onErrorContainer,
			) {
				Icon(
					imageVector = Icons.Default.Check,
					contentDescription = "Icon Check"
				)
			}
		},
		topBar = {
			CenterAlignedTopAppBar(
				windowInsets = WindowInsets(30, 0, 30, 0),
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = MaterialTheme.colorScheme.primaryContainer,
//					titleContentColor = MaterialTheme.colorScheme.primary,
				),
				title = { Text("Menu ") },
				navigationIcon = {
					IconButton(onClick = {}) {
						Icon(
							imageVector = Icons.Default.Menu,
							contentDescription = "Icon Menu"
						)
					}
				},
				actions = {
					BadgedBox(
						badge = {
							Badge() {
								Text(text = "100")
							}
						}
					) {
						IconButton(onClick = {}) {
							Icon(
								imageVector = Icons.Outlined.Notifications,
								contentDescription = "Icon Notification",
								modifier = modifier.size(28.dp)
							)
						}
					}
				}
			)
		},
		bottomBar = {
			NavigationBar(
				windowInsets = WindowInsets(30, 0, 30, 0),
			) {
				NavigationBarItem(
					selected = false,
					onClick = {},
					icon = {
						Icon(
							imageVector = Icons.Default.Home,
							contentDescription = "Icon Home"
						)
					}
				)
				NavigationBarItem(
					selected = false,
					onClick = {},
					icon = {
						Icon(
							imageVector = Icons.Default.ShoppingCart,
							contentDescription = "Icon Home"
						)
					}
				)
				FloatingActionButton(onClick = {}) {
					Icon(
						imageVector = Icons.Default.Add,
						contentDescription = "Icon Home"
					)
				}

				NavigationBarItem(
					selected = false,
					onClick = {},
					icon = {
						Icon(
							imageVector = Icons.Default.Email,
							contentDescription = "Icon Home"
						)
					}
				)
				NavigationBarItem(
					selected = false,
					onClick = {},
					icon = {
						Icon(
							imageVector = Icons.Default.Settings,
							contentDescription = "Icon Home"
						)
					}
				)
			}
		}
	) { paddingValue ->
		Box(
			modifier = modifier
				.padding(paddingValue)
				.fillMaxSize(),
		) {
			Column(modifier = modifier.fillMaxSize()) {
				Row(
					modifier = modifier
						.fillMaxWidth()
						.padding(horizontal = 20.dp, vertical = 20.dp),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically
				) {
					Row {
						Text(
							text = "All", fontWeight = FontWeight.SemiBold,
							fontSize = 26.sp
						)
						Spacer(modifier = modifier.size(4.dp))
						Text(
							text = "Products", fontWeight = FontWeight.Light,
							fontSize = 24.sp
						)
					}
					TextButton(onClick = {}) {
						Text(
							"Filter ",
							fontWeight = FontWeight.SemiBold,
							textDecoration = TextDecoration.Underline,
							fontSize = 16.sp
						)
						Icon(
							imageVector = Icons.Default.ArrowDropDown,
							contentDescription = "Filter"
						)
					}
				}
				ListProduct()
			}
		}
	}
}

@Composable
fun ProductItem(
	item: Product,
	modifier: Modifier = Modifier
) {
	Card(
		modifier = modifier
			.fillMaxWidth()
			.padding(4.dp),
		elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
	) {
		Column(
			modifier = modifier
				.fillMaxWidth()
				.padding(horizontal = 12.dp, vertical = 16.dp),
//			verticalArrangement = Arrangement.spacedBy(5.dp),
		) {
			Row(
				modifier = modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.End
			) {
				IconButton(
					onClick = {},
					modifier = modifier.size(24.dp)
				) {
					Icon(
						imageVector = Icons.Outlined.FavoriteBorder,
						contentDescription = "Is favorite",
						modifier = modifier.size(20.dp)
					)
				}
			}
			Column(
				modifier = modifier.fillMaxWidth(),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.spacedBy(10.dp)
			) {
				Image(
					painter = painterResource(R.drawable.ic_launcher_foreground),
					contentDescription = "Is image product",
					contentScale = ContentScale.Crop,
					modifier = modifier
						.size(120.dp)
						.padding(8.dp)
						.background(
							MaterialTheme.colorScheme.primaryContainer,
							shape = CircleShape
						),
					// fix size
				)
				Text(
					item.name,
					fontSize = 14.sp,
					fontWeight = FontWeight.Medium
				)


				Text(
					"$${item.price}",
					fontSize = 12.sp,
					fontWeight = FontWeight.SemiBold
				)
			}
		}
	}
}

@Composable
fun ListProduct(modifier: Modifier = Modifier) {
	LazyVerticalGrid(
		modifier = modifier
			.fillMaxSize()
			.padding(horizontal = 8.dp),
		columns = GridCells.Fixed(2),
		contentPadding = PaddingValues(6.dp),
		horizontalArrangement = Arrangement.spacedBy(6.dp),
		verticalArrangement = Arrangement.spacedBy(6.dp),
	) {
		items(productExample.size, key = { it }) {
			ProductItem(item = productExample[it])
		}
	}
}

@Preview
@Composable
private fun ProductListScreenPrev() {
	ProductListScreen(
		navController = NavHostController(LocalContext.current),
		state = ProductState(),
		onEvent = {}
	)

}

@Preview(showBackground = true)
@Composable
private fun ListProductPrev() {
	ListProduct()
}

@Preview(showBackground = true)
@Composable
private fun ProductItemPrev() {
	ProductItem(
		item = productExample[0]
	)
}