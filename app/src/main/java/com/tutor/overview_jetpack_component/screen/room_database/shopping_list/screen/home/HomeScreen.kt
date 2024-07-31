package com.tutor.overview_jetpack_component.screen.room_database.shopping_list.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tutor.overview_jetpack_component.R
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.ItemWithStoreAndList
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Item
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Shopping
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Store
import com.tutor.overview_jetpack_component.ui.Category
import com.tutor.overview_jetpack_component.ui.Utils
import com.tutor.overview_jetpack_component.ui.theme.Shapes
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeShopScreen(
	onNavigate: (Int) -> Unit,
	modifier: Modifier = Modifier,
) {
	val viewModel = viewModel(modelClass = HomeViewModel::class.java)
	val homeState = viewModel.state
	fun selectCategory(category: Category) {
		viewModel.onCategoryChange(category)
	}

	fun handlerDelete(item: Item) {
		viewModel.deleteItem(item)
	}
	Scaffold(
		floatingActionButton = {
			FloatingActionButton(onClick = {
				/*				not update*/
				onNavigate.invoke(-1)
			}) {
				Icon(imageVector = Icons.Default.Add, contentDescription = "icon add")
			}
		}
	) { paddingValue ->
		LazyColumn(modifier.padding(paddingValue)) {
			item {
				LazyRow {
					items(Utils.category) { category ->
						CategoryItem(
							item = category,
							selected = category == homeState.category,
							onClick = { selectCategory(category) })
					}
				}
			}
			items(homeState.items) {
				ShoppingItems(item = it,
					isCheck = it.itemList.isChecked,
					onCheckedChange = viewModel::onItemCheckedChange,
					handlerDelete = { item -> handlerDelete(item) },
					onitemClick = { onNavigate.invoke(it.itemList.itemId) })
			}
		}
	}
}

@Composable
fun ShoppingItems(
	item: ItemWithStoreAndList,
	isCheck: Boolean,
	onCheckedChange: (Item, Boolean) -> Unit,
	onitemClick: () -> Unit,
	handlerDelete: (Item) -> Unit,
	modifier: Modifier = Modifier
) {
	Card(
		modifier = modifier
			.fillMaxWidth()
			.selectable(
				selected = isCheck,
				onClick = { onitemClick.invoke() },
			)
			.padding(8.dp)
	) {
		Row(
			modifier = modifier
				.fillMaxWidth()
				.padding(8.dp),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Column(
				modifier = Modifier.padding(8.dp),
				verticalArrangement = Arrangement.spacedBy(4.dp)
			) {
				Text(
					text = item.itemList.itemName,
					style = MaterialTheme.typography.titleMedium,
					fontWeight = FontWeight.Bold
				)

				Text(
					text = item.storeList.storeName,
					style = MaterialTheme.typography.bodySmall,
				)
				CompositionLocalProvider(
					LocalContentColor provides
							MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
				) {
					Text(
						text = formatDate(item.itemList.date),
						style = MaterialTheme.typography.bodySmall
					)
				}
			}
			Column(
				modifier = modifier,
				horizontalAlignment = Alignment.End
			) {
				Row(
					verticalAlignment = Alignment.CenterVertically
				) {
					Text(
						text = "Qty : ${item.itemList.qty}",
						style = MaterialTheme.typography.titleMedium,
						fontWeight = FontWeight.Bold
					)
					Checkbox(
						checked = isCheck,
						onCheckedChange = { onCheckedChange(item.itemList, it) }
					)
				}

				IconButton(onClick = { handlerDelete(item.itemList) }) {
					Icon(
						imageVector = Icons.Default.Delete,
						contentDescription = null
					)
				}
			}
		}
	}

}

@Preview
@Composable
private fun ShoppingItemsPrev() {
	ShoppingItems(
		item = ItemWithStoreAndList(
			itemList = Item(
				itemId = 1,
				itemName = "Item 1",
				qty = "1",
				shoppingIdFk = 1,
				storeIdFk = 1,
				date = Date(),
				isChecked = false
			),
			shoppingList = Shopping(
				shoppingId = 1,
				shoppingName = "Shopping 1"
			),
			storeList = Store(
				storeId = 1,
				listIdFk = 1,
				storeName = "Store 1"
			)
		),
		isCheck = false,
		onCheckedChange = { _, _ -> },
		onitemClick = {}, handlerDelete = {}
	)

}

fun formatDate(date: Date): String {
	return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
		.format(date)
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CategoryItem(
	item: Category,
	selected: Boolean,
	onClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Surface(selected = selected, onClick = onClick) {
		Card(
			modifier = modifier.padding(8.dp),
			border = BorderStroke(
				1.dp,
				if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
				else MaterialTheme.colorScheme.onSurface,
			),
			shape = Shapes.large,
			colors = CardDefaults.cardColors(
				containerColor = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
				else MaterialTheme.colorScheme.surface,
				contentColor = if (selected) MaterialTheme.colorScheme.onPrimary
				else MaterialTheme.colorScheme.onSurface
			)
		) {
			Row(
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically,
				modifier = modifier.padding(8.dp)
			) {
				Icon(
					painter = painterResource(id = item.resId),
					contentDescription = null,
					modifier = modifier.size(24.dp)
				)
				Spacer(modifier.size(8.dp))
				Text(
					text = item.title,
					style = MaterialTheme.typography.titleSmall,
//				color = Color.White
				)
			}
		}
	}

}

@Preview(showBackground = true)
@Composable
private fun HomeShopScreenPrev() {
	val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
	HomeShopScreen(onNavigate = {})
}

@Preview(showBackground = true)
@Composable
private fun CategoryItemPrev() {
	CategoryItem(
		item = Category(
			id = 1,
			title = "Category 1",
			resId = R.drawable.ic_fruits
		),
		selected = true,
		onClick = {}
	)

}