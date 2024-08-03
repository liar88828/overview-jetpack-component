package com.tutor.overview_jetpack_component.screen.retrofit.user2.data

import com.tutor.overview_jetpack_component.screen.retrofit.user2.common.User2Constant
import com.tutor.overview_jetpack_component.screen.retrofit.user2.data.model.Users2
import retrofit2.Response
import retrofit2.http.GET

interface User2InterfaceAPI {
	@GET(User2Constant.USER)
	suspend fun getUsers(): Response<Users2>

}