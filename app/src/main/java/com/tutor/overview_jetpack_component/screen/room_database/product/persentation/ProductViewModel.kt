package com.tutor.overview_jetpack_component.screen.room_database.product.persentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutor.overview_jetpack_component.screen.room_database.product.data.Product
import com.tutor.overview_jetpack_component.screen.room_database.product.data.ProductDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//inner
//val ProductViewModel = viewModel(modelClass = ProductViewModel::class.java)

class ProductViewModel(
	private val dao: ProductDao = ProductGraph.dao,
) : ViewModel() {
	private val _sort = MutableStateFlow(true)
	private val _state = MutableStateFlow(ProductState())

	@OptIn(ExperimentalCoroutinesApi::class)
	private val product = _sort
		.flatMapLatest { sort ->
			if (sort) dao.getAllName()
			else dao.getAllPrice()
		}
		.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

	//
	val state = combine(_state, _sort, product)
	{ state, sortType, product ->
		state.copy(products = product)
	}
		.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProductState())

	fun onEvent(event: ProductEvent) {
		when (event) {
			is ProductEvent.OnCreate -> {
				if (checkFormProduct()) return
				viewModelScope.launch {
					dao.create(productData())
				}
				clearFormProduct()
			}

			is ProductEvent.OnDelete -> {
				viewModelScope.launch {
					dao.delete(event.product)
				}
			}

			is ProductEvent.SetName -> {
				_state.value = _state.value.copy(
					name = event.name
				)
			}

			is ProductEvent.SetPrice -> {
				_state.update { it.copy(price = event.price) }
			}

			is ProductEvent.SetQty -> {
				_state.update { it.copy(qty = event.qty) }
			}

			is ProductEvent.SetDescription -> _state.update {
				it.copy(
					description = event.description
				)
			}

			is ProductEvent.OnUpdate -> {
				if (checkFormProduct()) return
				viewModelScope.launch {
					dao.update(
						productData()
					)
				}

				clearFormProduct()
			}

			is ProductEvent.GetById -> {
				viewModelScope.launch {
					dao.getById(event.id)
				}
			}

			ProductEvent.GetByName -> {
				viewModelScope.launch {
					dao.getAllName()
				}

			}

			ProductEvent.GetByPrice -> {
				viewModelScope.launch {
					dao.getAllPrice()
				}

			}
		}

	}

	private fun productData() = Product(
		name = _state.value.name,
		qty = _state.value.qty,
		price = _state.value.price,
		description = _state.value.description
	)

	private fun checkFormProduct(): Boolean {
		return _state.value.name.isBlank() ||
				_state.value.qty.isBlank() ||
				_state.value.price.isBlank() ||
				_state.value.description.isBlank()
	}

	private fun clearFormProduct() {
		_state.update {
			it.copy(
				name = "",
				qty = "",
				price = "",
				description = ""
			)
		}
	}

}