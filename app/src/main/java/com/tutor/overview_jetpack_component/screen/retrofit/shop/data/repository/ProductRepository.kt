package com.tutor.overview_jetpack_component.screen.retrofit.shop.data.repository

import com.tutor.overview_jetpack_component.screen.retrofit.shop.data.Result
import com.tutor.overview_jetpack_component.screen.retrofit.shop.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
	suspend fun getProducts(): Flow<Result<List<Product>>>
}