package com.tutor.overview_jetpack_component.screen.retrofit.album.data.model

import retrofit2.Response
import retrofit2.http.GET

interface AlbumApi {
	@GET(ALBUMS)
	suspend fun getAlbums(): Response<Albums>

	companion object {
		const val BASE_URL = "https://jsonplaceholder.typicode.com"
		const val ALBUMS = "/albums"
	}

}