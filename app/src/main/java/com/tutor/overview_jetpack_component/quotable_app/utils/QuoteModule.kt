package com.tutor.overview_jetpack_component.quotable_app.utils

import com.tutor.overview_jetpack_component.quotable_app.data.QuotableApi
import com.tutor.overview_jetpack_component.quotable_app.data.QuotableConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object QuoteModule {
	fun provideOkHttpClient(): OkHttpClient {
		val okHttpClient = OkHttpClient().newBuilder()
		okHttpClient.callTimeout(40, TimeUnit.SECONDS)
		okHttpClient.connectTimeout(40, TimeUnit.SECONDS)
		okHttpClient.readTimeout(40, TimeUnit.SECONDS)
		okHttpClient.writeTimeout(40, TimeUnit.SECONDS)
		okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
		return okHttpClient.build()
	}

	val quoteApi: QuotableApi = Retrofit.Builder()
		.baseUrl(QuotableConstant.BASE_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.client(provideOkHttpClient())
		.build()
		.create(QuotableApi::class.java)

}