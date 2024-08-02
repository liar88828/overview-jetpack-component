package com.tutor.overview_jetpack_component.screen.retrofit.cat_fact.utils

import com.tutor.overview_jetpack_component.screen.retrofit.cat_fact.data.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
	val api: ApiInterface by lazy {
		Retrofit.Builder()
			.baseUrl(Apis.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build().create(ApiInterface::class.java)
	}
}