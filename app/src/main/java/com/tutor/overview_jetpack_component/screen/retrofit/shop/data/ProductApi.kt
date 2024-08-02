package com.tutor.overview_jetpack_component.screen.retrofit.shop.data

import com.tutor.overview_jetpack_component.screen.retrofit.shop.data.model.Products
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
	@GET(PRODUCTS)
	suspend fun getProducts(): Response<Products>

	companion object {
		const val BASE_URL = "https://dummyjson.com"
		const val PRODUCTS = "/products"
	}

}