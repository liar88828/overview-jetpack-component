package com.tutor.overview_jetpack_component.screen.retrofit.shop.persentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tutor.overview_jetpack_component.screen.retrofit.shop.data.Result
import com.tutor.overview_jetpack_component.screen.retrofit.shop.data.model.Product
import com.tutor.overview_jetpack_component.screen.retrofit.shop.data.repository.ProductRepository
import com.tutor.overview_jetpack_component.screen.retrofit.shop.data.repository.ProductRepositoryImpl
import com.tutor.overview_jetpack_component.screen.retrofit.shop.utils.ProductRetrofitApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductRetroViewModel(
	private var repository: ProductRepository
) : ViewModel() {
	private val _products = MutableStateFlow<List<Product>>(emptyList())
	val products = _products.asStateFlow()

	private val _showError = Channel<Boolean>()
	val showError = _showError.receiveAsFlow()

	private val _message = Channel<String>()
	val messageRes = _message.receiveAsFlow()

	private fun getProducts() {
		viewModelScope.launch {
			repository.getProducts()
				.collectLatest { result ->
					when (result) {

						is Result.Success -> {
							_products.update { result.data ?: emptyList() }
//							_message.send(result.message ?: "Success")
//							result.data?.let {
//								_products.update { it }
//							}
							_showError.send(false)

						}

						is Result.Error -> {
//							_message.send(result.message ?: "Error")
							_showError.send(true)
						}
					}
				}
		}
	}

	init {
		getProducts()
	}

}

@Suppress("UNCHECKED_CAST")
class ProductRetroViewModelFactory(
//	private val repository: ProductRepository
) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return ProductRetroViewModel(ProductRepositoryImpl(ProductRetrofitApi.productApi)) as T
	}
}
