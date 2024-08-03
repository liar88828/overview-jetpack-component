package com.tutor.overview_jetpack_component.screen.retrofit.user2.domain.repository

import com.tutor.overview_jetpack_component.screen.retrofit.user2.data.model.Users2

interface User2Repository {
	suspend fun getUserList(): Users2

}