package com.tutor.overview_jetpack_component.screen.room_database.shopping_list.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Store
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.screen.home.CategoryItem
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.screen.home.formatDate
import com.tutor.overview_jetpack_component.ui.Category
import com.tutor.overview_jetpack_component.ui.Utils
import com.tutor.overview_jetpack_component.ui.theme.Shapes
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@Composable
fun DetailShopScreen(
	id: Int,
	navigateUp: () -> Unit,
	modifier: Modifier = Modifier
) {
	val viewModel = viewModel<DetailViewModel>(factory = DetailViewModelFactory(id))
	Scaffold {
		DetailEntry(
			state = viewModel.state,
			onDateSelect = viewModel::onDateChange,
			onStoreChange = viewModel::onStoreChange,
			onItemChange = viewModel::onItemChange,
			onCategoryChange = viewModel::onCategoryChange,
			onDialogDismiss = viewModel::onDialogDismiss,
			onSaveStore = viewModel::onSaveStore,
			onQtyChange = viewModel::onQtyChange,
			updateItem = { viewModel.updateItem(id) },
			saveItem = viewModel::saveItem,
			modifier = modifier.padding(it),
			navigateUp = navigateUp,
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailEntry(
	state: DetailState,
	onDateSelect: (Date) -> Unit,
	onStoreChange: (String) -> Unit,
	onItemChange: (String) -> Unit,
	onQtyChange: (String) -> Unit,
	onCategoryChange: (Category) -> Unit,
	onDialogDismiss: (Boolean) -> Unit,
	onSaveStore: () -> Unit,
	updateItem: () -> Unit,
	saveItem: () -> Unit,
	navigateUp: () -> Unit,
	modifier: Modifier = Modifier
) {
	var isNewEnable by remember { mutableStateOf(false) }
	val isOpen = remember { mutableStateOf(false) }
	Column(
		modifier = modifier
			.fillMaxWidth()
			.padding(8.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		TextField(
			value = state.item,
			shape = Shapes.large,
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 16.dp),
			onValueChange = { onItemChange(it) },
			colors = TextFieldDefaults.colors(
				unfocusedIndicatorColor = Color.Transparent,
				focusedIndicatorColor = Color.Transparent,
			),
			label = { Text("Item") },
		)

		TextField(
			value = state.store,
			shape = Shapes.large,
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 16.dp),
			onValueChange = { if (isNewEnable) onStoreChange(it) }, label = { Text("Store") },
			colors = TextFieldDefaults.colors(
				unfocusedIndicatorColor = Color.Transparent,
				focusedIndicatorColor = Color.Transparent,
			),
			leadingIcon = {
				IconButton(onClick = { onDialogDismiss(!state.isScreenDialog) }) {
					Icon(
						imageVector = Icons.Default.Add, contentDescription = "Icon Key Down",
						modifier = modifier
					)
				}
			},
			trailingIcon = {
				TextButton(onClick = {
					isNewEnable = if (isNewEnable) {
						onSaveStore()
						!isNewEnable
					} else {
						!isNewEnable
					}
				}) {
					Text(text = if (isNewEnable) "Save" else "New")
				}
			}
		)





		if (state.isScreenDialog) {
			Popup(onDismissRequest = { onDialogDismiss(!state.isScreenDialog) }) {
				PopUpColumn(modifier, state, onStoreChange, onDialogDismiss)
			}
		}
		TextField(
			value = state.qty,
			shape = Shapes.large,
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 16.dp),
			onValueChange = { onQtyChange(it) },
			colors = TextFieldDefaults.colors(
				unfocusedIndicatorColor = Color.Transparent,
				focusedIndicatorColor = Color.Transparent,
			),
			label = { Text("Qty") },
		)
//		SelectDateAndQty(state, showDatePicker, datePickerState, onQtyChange)
		Row(verticalAlignment = Alignment.CenterVertically) {
			if (isOpen.value) {
				CustomDatePickerDialog(
					onAccept = {
						isOpen.value = false // close dialog
					},
					onCancel = {
						isOpen.value = false //close dialog
					}
				)
			}
			CustomDatePicker(state, onDateSelect = { onDateSelect(it) })
		}

		LazyRow() {
			items(Utils.category) { category ->
				CategoryItem(
					item = Category(
						id = category.id,
						title = category.title,
						resId = category.resId
					),
					selected = category.id == state.category.id,
					onClick = { onCategoryChange(category) }
				)
//				Spacer(modifier.size(16.dp))
			}
		}
		val buttonTitle = if (state.isUpdating) "Update" else "Save"
		Button(
			modifier = modifier.fillMaxWidth(),
			enabled = state.item.isNotEmpty()
					&& state.store.isNotEmpty()
					&& state.qty.isNotEmpty(),
			onClick = {
				when (state.isUpdating) {
					true -> updateItem()
					false -> onSaveStore()
				}
				navigateUp()
			},
			shape = Shapes.large
		) { Text(text = buttonTitle) }
	}

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SelectDateAndQty(
	state: DetailState,
	showDatePicker: Boolean,
	datePickerState: DatePickerState,
	onQtyChange: (String) -> Unit
) {
	var showDatePicker1 = showDatePicker
	Row(modifier = Modifier.fillMaxWidth()) {
		Row(
			modifier = Modifier.weight(0.5f),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Text(
				formatDate(state.date),
				fontSize = 16.sp
			)
			if (showDatePicker1) {
				DatePickerDialog(
					onDismissRequest = {},
					confirmButton = {
						TextButton(onClick = { showDatePicker1 = false }) {
							Text("OK")
						}
					}, dismissButton = {
						TextButton(onClick = { showDatePicker1 = false }) {
							Text("OK")
						}
					}
				) {
					DatePicker(datePickerState)
				}
			}

			IconButton(onClick = { showDatePicker1 = true }) {
				Icon(
					imageVector = Icons.Default.DateRange,
					contentDescription = "Icon Date Range",
				)
			}
		}


		TextField(
			value = state.qty,
			shape = Shapes.large,
			keyboardOptions = KeyboardOptions(
				keyboardType = KeyboardType.Number
			),
			modifier = Modifier
				.weight(0.5f)
				.padding(bottom = 16.dp),
			onValueChange = { onQtyChange(it) },
			colors = TextFieldDefaults.colors(
				unfocusedIndicatorColor = Color.Transparent,
				focusedIndicatorColor = Color.Transparent,
			),
			label = { Text("Qty") },
		)
	}
}

@Composable
fun PopUpColumn(
	modifier: Modifier,
	state: DetailState,
	onStoreChange: (String) -> Unit,
	onDialogDismiss: (Boolean) -> Unit
) {
	Surface {
		Column(modifier = modifier.padding(8.dp)) {
			state.storeList.forEach {
				TextButton(onClick = {
					onStoreChange(it.storeName)
					onDialogDismiss(!state.isScreenDialog)
				}) {
					Text(text = it.storeName)
				}
			}
		}
	}
}

@Preview
@Composable
private fun PopUpColumnPrev() {
	PopUpColumn(
		modifier = Modifier,
		state = DetailState(
			storeList = listOf(
				Store(
					storeId = 1,
					listIdFk = 1,
					storeName = "Store 1"
				),
				Store(
					storeId = 2,
					listIdFk = 1,
					storeName = "Store 2"
				)
			)
		),
		onStoreChange = {},
		onDialogDismiss = {}
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun TestSelectDate() {
	val datePickerState = rememberDatePickerState()
	var showDatePicker by remember { mutableStateOf(false) }
	Column {
		//date picker
		Button(
			onClick = { showDatePicker = true },
			modifier = Modifier.fillMaxWidth(),
		) {
			Text(text = "Date Picker")
		}



		if (showDatePicker) {
			DatePickerDialog(
				onDismissRequest = {},
				confirmButton = {
					TextButton(onClick = { showDatePicker = false }) {
						Text("OK")
					}
				}, dismissButton = {
					TextButton(onClick = { showDatePicker = false }) {
						Text("OK")
					}
				}
			) {
				DatePicker(datePickerState)
			}
		}
	}
}

@Composable
fun CustomDatePicker(
	state: DetailState,
	onDateSelect: (Date) -> Unit,
) {
	val date = remember { mutableStateOf(LocalDate.now()) }
	val isOpen = remember { mutableStateOf(false) }

	TextField(
		colors = TextFieldDefaults.colors(
			unfocusedIndicatorColor = Color.Transparent,
			focusedIndicatorColor = Color.Transparent,
		),
		shape = Shapes.large,
		modifier = Modifier.fillMaxWidth(),
		readOnly = true,
		value = date.value.format(DateTimeFormatter.ISO_DATE),
		label = { Text("Date") },
		onValueChange = {}, trailingIcon = {
			IconButton(
				onClick = {
					isOpen.value = true
				} // show de dialog
			) {
				Icon(imageVector = Icons.Default.DateRange, contentDescription = "Calendar")
			}
		}
	)

	if (isOpen.value) {
		CustomDatePickerDialog(
			onAccept = {
				isOpen.value = false // close dialog
				if (it != null) { // Set the date
					date.value = Instant
						.ofEpochMilli(it)
						.atZone(ZoneId.of("UTC"))
						.toLocalDate()
					onDateSelect(
						Date.from(
							date.value.atStartOfDay()
								.atZone(ZoneId.systemDefault()).toInstant()
						)
					)
				}
			},
			onCancel = {
				isOpen.value = false //close dialog
			},
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
	onAccept: (Long?) -> Unit,
	onCancel: () -> Unit,
) {
	val state = rememberDatePickerState()

	DatePickerDialog(
		onDismissRequest = { },
		confirmButton = {
			Button(onClick = {
				onAccept(state.selectedDateMillis)
			}) {
				Text("Accept")
			}
		},
		dismissButton = {
			Button(onClick = onCancel) {
				Text("Cancel")
			}
		}
	) {
		DatePicker(state = state)
	}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailEntryPrev() {
	DetailEntry(
		state = DetailState(),
		onDateSelect = {},
		onStoreChange = {},
		onItemChange = {},
		onQtyChange = {},
		onCategoryChange = {},
		onDialogDismiss = {},
		onSaveStore = {},
		updateItem = {},
		saveItem = {},
		navigateUp = {}
	)

}
