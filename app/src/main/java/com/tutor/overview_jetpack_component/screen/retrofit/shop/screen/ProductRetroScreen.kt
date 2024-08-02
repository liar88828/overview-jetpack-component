package com.tutor.overview_jetpack_component.screen.retrofit.shop.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.tutor.overview_jetpack_component.screen.retrofit.shop.data.model.Product
import com.tutor.overview_jetpack_component.screen.retrofit.shop.data.model.productExample

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductRetroScreen(
	products: List<Product>,
	navController: NavHostController,
	modifier: Modifier = Modifier
) {
	Scaffold(
		bottomBar = {
			NavigationBar() {
				NavigationBarItem(
					selected = false,
					onClick = { /*TODO*/ },
					icon = {
						Icon(
							imageVector = Icons.Default.Home,
							contentDescription = "Home icon"
						)
					}
				)
				FloatingActionButton(
					onClick = { /*TODO*/ },

					)
				{ Icon(imageVector = Icons.Default.Add, contentDescription = "Add icon") }


				NavigationBarItem(
					selected = false,
					onClick = { /*TODO*/ },
					icon = {
						Icon(
							imageVector = Icons.Default.Settings,
							contentDescription = "Setting icon"
						)
					}
				)
			}
		},

		topBar = {
			TopAppBar(
				windowInsets = WindowInsets(40, 40, 40, 0),
				navigationIcon = {
					IconButton({}) {
						Icon(
							imageVector = Icons.AutoMirrored.Filled.ArrowBack,
							contentDescription = "Home icon"
						)
					}
				},
				title = {
					Text(text = "Product List")
				}
			)
		}
	) { paddingValue ->
		Surface(
			modifier = Modifier.padding(paddingValue)
		) {

			if (products.isEmpty()) {
				LoadingData(modifier)
			}
			LazyColumn(
				modifier = modifier
					.fillMaxSize()
					.padding(8.dp),
				verticalArrangement = Arrangement.spacedBy(10.dp),
				contentPadding = PaddingValues(8.dp)
			) {
				items(products) {
					ProductRetroItem(it)
				}

			}
		}
	}
}

@Composable
fun ProductRetroItem(item: Product, modifier: Modifier = Modifier) {
	Card(
		modifier = modifier.fillMaxWidth(),

		colors = CardDefaults.cardColors(
			containerColor = MaterialTheme.colorScheme.primaryContainer,
			contentColor = MaterialTheme.colorScheme.onPrimaryContainer
		)
	) {
		AsyncImage(
			model = item.images[0], contentDescription = item.title,
			contentScale = ContentScale.Crop,
			modifier = modifier
				.fillMaxWidth()
				.size(180.dp),
		)

//		Image(
//			painter = painterResource(R.drawable.ic_launcher_foreground),
//			contentDescription = "Example Image",
//			contentScale = ContentScale.Crop,
//			modifier = modifier
//				.fillMaxWidth()
//				.size(180.dp),
//		)

		Column(
			modifier = modifier
				.fillMaxWidth()
				.background(
					MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
				)
				.padding(16.dp),
			verticalArrangement = Arrangement.spacedBy(12.dp)
		) {
			Text(
				text = item.title,
				fontSize = 20.sp,
				fontWeight = FontWeight.Medium,
			)
			Row(
				modifier = modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.spacedBy(10.dp)
			) {
				Text(
					text = "Price : ${item.price}$",
					fontSize = 18.sp,
					fontWeight = FontWeight.Medium
				)

				Text(
					text = "-${item.discountPercentage}%",
					fontSize = 18.sp,
					fontWeight = FontWeight.Medium
				)
			}
			Text(
				text = item.description,
				fontSize = 18.sp,
				fontWeight = FontWeight.Medium,
				maxLines = 2,
				lineHeight = 24.sp,
				overflow = TextOverflow.Ellipsis

			)
		}

	}
}

@Preview//(showBackground = true, showSystemUi = true)
@Composable
private fun ProductRetroItemPrev() {
	ProductRetroItem(item = productExample)
}

@Preview(showBackground = true)
@Composable
private fun ProductRetroScreenPrev() {
	ProductRetroScreen(
		products = listOf(
			productExample,
			productExample,
			productExample,
			productExample,
		), navController = rememberNavController()
	)
}

@Composable
private fun LoadingData(modifier: Modifier) {
	Box(
		modifier = modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		CircularProgressIndicator()
	}
}
