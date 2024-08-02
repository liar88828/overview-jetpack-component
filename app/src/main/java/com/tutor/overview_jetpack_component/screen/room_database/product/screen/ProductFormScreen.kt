package com.tutor.overview_jetpack_component.screen.room_database.product.screen

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tutor.overview_jetpack_component.screen.room_database.product.persentation.ProductEvent
import com.tutor.overview_jetpack_component.screen.room_database.product.persentation.ProductState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormScreen(
	navController: NavHostController,
	state: ProductState,
	onEvent: (ProductEvent) -> Unit,
	modifier: Modifier = Modifier
) {
	Scaffold(
		modifier = modifier.fillMaxSize(),
		floatingActionButton = {
			FloatingActionButton(onClick = {}) {
				Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = "Icon Send")
			}
		},
		topBar = {
			CenterAlignedTopAppBar(
				colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
					containerColor = MaterialTheme.colorScheme.primaryContainer,
				),
				windowInsets = WindowInsets(40, 0, 40, 0),
				navigationIcon = {
					IconButton(onClick = { navController.navigateUp() }) {
						Icon(
							imageVector = Icons.AutoMirrored.Filled.ArrowBack,
							contentDescription = "Icon Menu"
						)
					}
				},
				actions = {
					IconButton(onClick = { }) {
						Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Icon More")
					}
				},
				title = {
					Text(text = "Form add Product")
				}
			)
		}
	) { paddingValue ->
		Box(
			modifier = modifier
				.padding(paddingValue)
				.fillMaxSize()
				.padding(top = 40.dp),
//			contentAlignment = Alignment.Center
		) {
			Card(
				colors = CardDefaults.cardColors(
					containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
				),
				modifier = modifier
					.fillMaxWidth()
					.padding(20.dp),
			) {
				Column(
					modifier = modifier
						.fillMaxWidth()
						.padding(horizontal = 20.dp, vertical = 40.dp),
					verticalArrangement = Arrangement.spacedBy(10.dp)
				) {
					OutlinedTextField(
						modifier = modifier.fillMaxWidth(),
						colors = OutlinedTextFieldDefaults.colors(
							focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
							unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
							focusedBorderColor = Color.Transparent,
							unfocusedBorderColor = Color.Transparent
						),
						leadingIcon = {
							Icon(
								imageVector = Icons.Outlined.Inventory2,
								contentDescription = "Icon Packet"
							)
						},
						label = { Text(text = "Name") },
						value = state.name,
						onValueChange = { onEvent(ProductEvent.SetName(it)) },
						keyboardOptions = KeyboardOptions(
							autoCorrect = true,
							keyboardType = KeyboardType.Text,
							imeAction = ImeAction.Next,
							capitalization = KeyboardCapitalization.Words
						)
					)
					OutlinedTextField(
						modifier = modifier.fillMaxWidth(),
						colors = OutlinedTextFieldDefaults.colors(
							focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
							unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
							focusedBorderColor = Color.Transparent,
							unfocusedBorderColor = Color.Transparent
						),
						leadingIcon = {
							Icon(
								imageVector = Icons.Default.AttachMoney,
								contentDescription = "Icon Price"
							)
						},
						label = { Text(text = "Price") },
						value = state.price,
						onValueChange = { onEvent(ProductEvent.SetPrice(it)) },
						keyboardOptions = KeyboardOptions(
							autoCorrect = true,
							keyboardType = KeyboardType.Number,
							imeAction = ImeAction.Next,
						)
					)
					OutlinedTextField(
						modifier = modifier.fillMaxWidth(),
						colors = OutlinedTextFieldDefaults.colors(
							focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
							unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
							focusedBorderColor = Color.Transparent,
							unfocusedBorderColor = Color.Transparent
						),
						leadingIcon = {
							Icon(
								imageVector = Icons.Outlined.Add,
								contentDescription = "Icon Add Product"
							)
						},
						label = { Text(text = "Quantity") },
						value = state.qty,
						onValueChange = { onEvent(ProductEvent.SetQty(it)) },
						keyboardOptions = KeyboardOptions(
							autoCorrect = true,
							keyboardType = KeyboardType.Number,
							imeAction = ImeAction.Next,
						)
					)

					OutlinedTextField(
						modifier = modifier.fillMaxWidth(),
						colors = OutlinedTextFieldDefaults.colors(
							focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
							unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
							focusedBorderColor = Color.Transparent,
							unfocusedBorderColor = Color.Transparent
						),
						leadingIcon = {
							Icon(
								imageVector = Icons.Outlined.Description,
								contentDescription = "Icon Description"
							)
						},
						label = {
							Text(
								text = "Description",
							)
						},
						value = state.description,
						onValueChange = { onEvent(ProductEvent.SetDescription(it)) },
						keyboardOptions = KeyboardOptions(
							autoCorrect = true,
							keyboardType = KeyboardType.Text,
							imeAction = ImeAction.Next,
							capitalization = KeyboardCapitalization.Words
						)
					)
					Spacer(modifier = modifier.size(8.dp))

					Row(
						modifier = modifier.fillMaxWidth(),
						verticalAlignment = Alignment.CenterVertically,
					) {
						Checkbox(checked = false, onCheckedChange = {}
						)
						Text(text = "I Have read, understand and agree to the Terms and Conditions")
					}

					Button(
						modifier = modifier
							.padding(top = 20.dp)
							.align(alignment = Alignment.End),
						onClick = { onEvent(ProductEvent.OnCreate) },
					) {
						Spacer(modifier = modifier.size(8.dp))

						Text(
							text = "Save",
							fontSize = 16.sp,
							fontWeight = FontWeight.SemiBold
						)
						Spacer(modifier = modifier.size(8.dp))
						Icon(
							imageVector = Icons.AutoMirrored.Filled.Send,
							contentDescription = "Icon Send"
						)
						Spacer(modifier = modifier.size(8.dp))
					}
				}
			}
		}
	}
}

@Preview
@Composable
private fun ProductFormScreenPrev() {
	ProductFormScreen(
		navController = NavHostController(LocalContext.current),
		state = ProductState(),
		onEvent = {}
	)
}