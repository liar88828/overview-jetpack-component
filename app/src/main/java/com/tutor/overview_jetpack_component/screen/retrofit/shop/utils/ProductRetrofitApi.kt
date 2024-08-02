package com.tutor.overview_jetpack_component.screen.retrofit.shop.utils

import com.tutor.overview_jetpack_component.screen.retrofit.shop.data.ProductApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductRetrofitApi {

	private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
		level = HttpLoggingInterceptor.Level.BODY
	}

	private val client: OkHttpClient = OkHttpClient.Builder()
		.addInterceptor(interceptor)
		.build()

	val productApi: ProductApi = Retrofit.Builder()
		.baseUrl(ProductApi.BASE_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.client(client)
		.build()
		.create(ProductApi::class.java)
}