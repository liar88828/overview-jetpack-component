package com.tutor.overview_jetpack_component.screen.room_database.product.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tutor.overview_jetpack_component.R
import com.tutor.overview_jetpack_component.screen.room_database.product.data.productExample
import com.tutor.overview_jetpack_component.screen.room_database.product.persentation.ProductEvent
import com.tutor.overview_jetpack_component.screen.room_database.product.persentation.ProductState

@OptIn(
	ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
	ExperimentalFoundationApi::class
)
@Composable
fun ProductDetailScreen(
	navController: NavHostController,
	state: ProductState,
	onEvent: (ProductEvent) -> Unit,
	modifier: Modifier = Modifier
) {
	val item = productExample[1]
	val pagerState = rememberPagerState(pageCount = { 3 })
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
		bottomBar = {
			BottomAppBar(
				windowInsets = WindowInsets(40, 0, 40, 30),
			) {
				Row(
					horizontalArrangement = Arrangement.SpaceBetween,
					modifier = modifier.fillMaxWidth()
				) {
					FloatingActionButton(onClick = {}) {
						Icon(
							imageVector = Icons.Outlined.FavoriteBorder,
							contentDescription = "Is favorite",
						)
					}

					FloatingActionButton(
						onClick = { /*TODO*/ },
						containerColor = MaterialTheme.colorScheme.primaryContainer,
					) {
						Icon(
							imageVector = Icons.Default.MailOutline,
							contentDescription = "Message Icon",
						)
					}
					ExtendedFloatingActionButton(
						onClick = { /*TODO*/ },
						containerColor = MaterialTheme.colorScheme.inversePrimary,
					) {
						Spacer(modifier = modifier.size(30.dp))
						Icon(
							imageVector = Icons.Default.ShoppingCart,
							contentDescription = "Icon trolley",
						)
						Spacer(modifier = modifier.size(8.dp))
						Text(text = "Add to Cart")
						Spacer(modifier = modifier.size(30.dp))
					}
				}
			}
		},
		topBar = {
			CenterAlignedTopAppBar(
				windowInsets = WindowInsets(40, 0, 40, 0),
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = MaterialTheme.colorScheme.primaryContainer,
//					titleContentColor = MaterialTheme.colorScheme.primary,
				),
				title = { Text("Menu ") },
				navigationIcon = {
					IconButton(onClick = {}) {
						Icon(
							imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
		}) { paddingValue ->
		Box(
			modifier = modifier
				.padding(paddingValue)
				.fillMaxSize()
//			contentAlignment = Alignment.Center
		) {
			Column() {
				Column(
					modifier = modifier
						.fillMaxWidth()
						.background(
							MaterialTheme.colorScheme.primaryContainer,
//							shape = CircleShape
						)
						.padding(vertical = 60.dp),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					HorizontalPager(
						state = pagerState,
						verticalAlignment = Alignment.CenterVertically,
					) { page ->
						Column(
							modifier = modifier.fillMaxWidth(),
							horizontalAlignment = Alignment.CenterHorizontally,
							verticalArrangement = Arrangement.spacedBy(20.dp)
						) {
							Image(
								painter = painterResource(R.drawable.ic_launcher_foreground),
								contentDescription = "Is image product",
								contentScale = ContentScale.Crop, modifier = modifier
									.size(230.dp)
									.background(
										MaterialTheme.colorScheme.primary,
										shape = CircleShape
									)
							)
//							Text(
//								text = "Page: $page",
//								modifier = Modifier
//							)
							Row(
								Modifier
									.wrapContentHeight()
									.fillMaxWidth()
//									.align(Alignment.BottomCenter)
									.padding(bottom = 8.dp),
								horizontalArrangement = Arrangement.Center
							) {
								repeat(pagerState.pageCount) { iteration ->
									val color =
										if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
									Box(
										modifier = Modifier
											.padding(2.dp)
											.clip(CircleShape)
											.background(color)
											.size(10.dp)
									)
								}
							}
						}
					}
				}
				Column(
					modifier = modifier
						.fillMaxWidth()
						.padding(30.dp),
					verticalArrangement = Arrangement.spacedBy(20.dp)
				) {
					Row(
						modifier = modifier
							.fillMaxWidth()
//						.padding(horizontal = 20.dp, vertical = 20.dp),
						, horizontalArrangement = Arrangement.SpaceBetween
					) {
						Text(
							text = item.name,
							fontSize = 20.sp,
							fontWeight = FontWeight.Bold,
							modifier = modifier.weight(0.2f),
							maxLines = 1,
							overflow = TextOverflow.Ellipsis
						)
						Spacer(modifier = modifier.size(4.dp))
						Text(
							text = "$${item.price}",
							fontSize = 20.sp,
							fontWeight = FontWeight.Bold
						)
					}
					Column(
						modifier = modifier
							.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(2.dp)
					) {
						Text(
							text = "Description",
							fontSize = 16.sp,
							fontWeight = FontWeight.SemiBold
						)
						Text(
							text = item.description,
							fontSize = 16.sp,
							fontWeight = FontWeight.Light,
							lineHeight = 30.sp,
							textAlign = TextAlign.Justify
						)
					}
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailScreenPrev() {
	ProductDetailScreen(
		navController = NavHostController(LocalContext.current),
		state = ProductState(),
		onEvent = {}
	)
}
