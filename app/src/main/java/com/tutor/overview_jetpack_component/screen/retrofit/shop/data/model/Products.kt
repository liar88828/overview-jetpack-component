package com.tutor.overview_jetpack_component.screen.retrofit.shop.data.model

data class Products(
	val limit: Int,
	val products: List<Product>,
	val skip: Int,
	val total: Int
)