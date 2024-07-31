package com.tutor.overview_jetpack_component.screen.room_database.shopping_list.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.Graph
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.ItemWithStoreAndList
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Item
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.repository.Repository
import com.tutor.overview_jetpack_component.ui.Category
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
	private val repository: Repository = Graph.repository
) : ViewModel() {
	var state by mutableStateOf(HomeState())
		private set

	fun onItemCheckedChange(item: Item, isChecked: Boolean) {
		viewModelScope.launch {
			repository.update(item.copy(isChecked = isChecked))
		}
	}

	init {
		getItems()
	}

	fun onCategoryChange(category: Category) {
		state = state.copy(category = category)
		filterBy(category.id)
	}

	private fun filterBy(shoppingId: Int) {
		if (shoppingId != 10001) {
			viewModelScope.launch {
				repository
					.getShoppingId(shoppingId)
					.collectLatest { state = state.copy(items = it) }
			}
		} else {
			getItems()
		}
	}

	private fun getItems() {
		viewModelScope.launch {
			repository.getItemsWithListAndStore.collectLatest {
				state = state.copy(items = it)
			}
		}
	}

	fun deleteItem(item: Item) {
		viewModelScope.launch {
			repository.deleteItem(item)
		}
	}

}

data class HomeState(
	val items: List<ItemWithStoreAndList> = emptyList(),
	val category: Category = Category(),
	val itemChecked: Boolean = false,
)