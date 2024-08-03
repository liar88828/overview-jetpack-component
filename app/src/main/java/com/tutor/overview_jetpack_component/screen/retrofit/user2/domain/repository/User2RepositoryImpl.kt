package com.tutor.overview_jetpack_component.screen.retrofit.user2.domain.repository

import com.tutor.overview_jetpack_component.screen.retrofit.user2.data.User2InterfaceAPI
import com.tutor.overview_jetpack_component.screen.retrofit.user2.data.model.Users2

class User2RepositoryImpl(
	private val api: User2InterfaceAPI
) : User2Repository {
	override suspend fun getUserList(): Users2 {

		return api.getUsers()
			.body()!!

	}

}