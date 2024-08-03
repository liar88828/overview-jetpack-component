package com.tutor.overview_jetpack_component.screen.retrofit.album.data.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AlbumInstance {

	val albumApi: AlbumApi = Retrofit.Builder()
		.baseUrl(AlbumApi.BASE_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
		.create(AlbumApi::class.java)

}