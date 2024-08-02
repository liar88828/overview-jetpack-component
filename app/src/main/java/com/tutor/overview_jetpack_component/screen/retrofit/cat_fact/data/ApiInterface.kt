package com.tutor.overview_jetpack_component.screen.retrofit.cat_fact.data

import com.tutor.overview_jetpack_component.screen.retrofit.cat_fact.model.CatFactModel
import com.tutor.overview_jetpack_component.screen.retrofit.cat_fact.utils.Apis
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
	@GET(Apis.FACT)
	suspend fun getCatFact(): Response<CatFactModel>

}
