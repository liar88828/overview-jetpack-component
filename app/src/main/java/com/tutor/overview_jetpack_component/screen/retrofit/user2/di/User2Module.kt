package com.tutor.overview_jetpack_component.screen.retrofit.user2.di

import com.tutor.overview_jetpack_component.screen.retrofit.user2.common.User2Constant
import com.tutor.overview_jetpack_component.screen.retrofit.user2.data.User2InterfaceAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//@Module
//@InstallIn(SingletonComponent::class)
class User2Module {

	//	@Provides
//	@Singleton
	fun provideUser2Repository(): User2InterfaceAPI {
		return Retrofit.Builder()
			.baseUrl(User2Constant.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(User2InterfaceAPI::class.java)
	}
}