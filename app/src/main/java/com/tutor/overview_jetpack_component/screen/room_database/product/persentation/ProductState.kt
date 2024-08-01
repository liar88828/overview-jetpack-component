package com.tutor.overview_jetpack_component.screen.room_database.product.persentation

import com.tutor.overview_jetpack_component.screen.room_database.product.data.Product

data class ProductState(
	val products: List<Product> = emptyList(),
	val name: String = "",
	val qty: String = "",
	val price: String = "",
	val description: String = "",
)
