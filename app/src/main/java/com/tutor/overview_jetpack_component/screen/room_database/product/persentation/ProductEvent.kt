package com.tutor.overview_jetpack_component.screen.room_database.product.persentation

import com.tutor.overview_jetpack_component.screen.room_database.product.data.Product

sealed interface ProductEvent {
	data object GetByName : ProductEvent
	data object GetByPrice : ProductEvent
	data class GetById(val id: Int) : ProductEvent
	data class OnDelete(val product: Product) : ProductEvent
	data class OnCreate(val product: Product) : ProductEvent
	data class OnUpdate(val product: Product) : ProductEvent
	data class SetName(val name: String) : ProductEvent
	data class SetQty(val qty: String) : ProductEvent
	data class SetPrice(val price: String) : ProductEvent
	data class SetDescription(val description: String) : ProductEvent

}