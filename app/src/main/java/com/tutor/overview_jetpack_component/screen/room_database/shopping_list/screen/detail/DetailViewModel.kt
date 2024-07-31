package com.tutor.overview_jetpack_component.screen.room_database.shopping_list.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.Graph
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Item
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Shopping
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.data.room.models.Store
import com.tutor.overview_jetpack_component.screen.room_database.shopping_list.repository.Repository
import com.tutor.overview_jetpack_component.ui.Category
import com.tutor.overview_jetpack_component.ui.Utils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date

class DetailViewModel(
	private val itemId: Int, private val repository: Repository = Graph.repository
) : ViewModel() {
	var state by mutableStateOf(DetailState())
		private set
	val isFieldNotEmpty: Boolean
		get() = state.item.isNotEmpty() && state.store.isNotEmpty() && state.qty.isNotEmpty()

	fun onItemChange(newValue: String) {
		state = state.copy(item = newValue)
	}

	fun onStoreChange(newValue: String) {
		state = state.copy(store = newValue)
	}

	fun onQtyChange(newValue: String) {
		state = state.copy(qty = newValue)
	}

	fun onDateChange(newValue: Date) {
		state = state.copy(date = newValue)
	}

	fun onCategoryChange(newValue: Category) {
		state = state.copy(category = newValue)
	}

	fun onDialogDismiss(newValue: Boolean) {
		state = state.copy(isScreenDialog = newValue)
	}

	private fun addListItem() {
		viewModelScope.launch {
			Utils.category.forEach {
				repository.insertShop(
					Shopping(shoppingId = it.id, shoppingName = it.title)
				)
			}
		}
	}

	fun saveItem() {
		viewModelScope.launch {
			repository.insertItem(
				Item(
					itemName = state.item,
					shoppingIdFk = state.category.id,
					date = state.date,
					qty = state.qty,
					storeIdFk = state.storeList.find {
						it.storeName == state.store
					}?.storeId ?: 0,
					isChecked = false,
					)
			)
		}
	}

	fun updateItem(id: Int) {
		viewModelScope.launch {
			repository.update(
				item = Item(
					itemName = state.item,
					shoppingIdFk = state.category.id,
					date = state.date,
					qty = state.qty,
					storeIdFk = state.storeList.find {
						it.storeName == state.store
					}?.storeId ?: 0,
					isChecked = false,
					itemId = id
				)
			)
		}
	}

	fun onSaveStore() {
		viewModelScope.launch {
			repository.insertStore(
				Store(
					storeName = state.store,
					listIdFk = state.category.id,
				)
			)
		}
	}

	fun getStores() {
		viewModelScope.launch {
			repository.store.collectLatest {
				state = state.copy(storeList = it)
			}
		}

	}

	init {
		state = if (itemId != -1) {
			state.copy(isScreenDialog = true)

		} else {
			state.copy(isScreenDialog = false)
		}
	}

	init {
		addListItem()
		getStores()
		if (itemId != -1) {
			viewModelScope.launch {
				repository.getItemId(itemId).collectLatest {
					state = state.copy(
						item = it.itemList.itemName,
						store = it.storeList.storeName,
						date = it.itemList.date,
						isUpdating = true,
						category = Utils.category.find { c ->
							c.id == it.shoppingList.shoppingId
						} ?: Category(),
						qty = it.itemList.qty,
					)
				}
			}

		}
	}
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
	private val id: Int
) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return DetailViewModel(itemId = id) as T
	}
}

data class DetailState(
	val storeList: List<Store> = emptyList(),
	val item: String = "",
	val store: String = "",
	val date: Date = Date(),
	val qty: String = "",
	val isScreenDialog: Boolean = true,
	val isUpdating: Boolean = false,
	val category: Category = Category()
)