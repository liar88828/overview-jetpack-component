package com.tutor.overview_jetpack_component.screen.retrofit.shop.data.repository

import com.tutor.overview_jetpack_component.screen.retrofit.shop.data.ProductApi
import com.tutor.overview_jetpack_component.screen.retrofit.shop.data.Result
import com.tutor.overview_jetpack_component.screen.retrofit.shop.data.model.Product
import com.tutor.overview_jetpack_component.screen.retrofit.shop.data.model.Products
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class ProductRepositoryImpl(
	private val api: ProductApi
) : ProductRepository {
	override suspend fun getProducts(): Flow<Result<List<Product>>> {
		return flow {
			val productFromApi = try {
				api.getProducts()
			} catch (e: IOException) {
				e.printStackTrace()
				emit(Result.Error("Couldn't load data"))
				return@flow
			} catch (e: HttpException) {
				e.printStackTrace()
				emit(Result.Error("An unknown error occured"))
				return@flow
			} catch (e: Exception) {
				e.printStackTrace()
				return@flow
			}
			if (productFromApi.isSuccessful && productFromApi.body() is Products && productFromApi.body() != null) {
				emit(Result.Success(productFromApi.body()!!.products))
			}
		}
	}
}