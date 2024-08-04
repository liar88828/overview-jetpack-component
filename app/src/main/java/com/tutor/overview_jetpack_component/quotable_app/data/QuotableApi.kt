package com.tutor.overview_jetpack_component.quotable_app.data

import com.tutor.overview_jetpack_component.quotable_app.data.quote.Quotes
import com.tutor.overview_jetpack_component.quotable_app.data.quote.QuotesItem
import retrofit2.Response
import retrofit2.http.GET

interface QuotableApi {
	@GET(QuotableConstant.GET_QUOTES)
	suspend fun getQuoteList(): Response<Quotes>

	@GET(QuotableConstant.GET_RANDOM_QUOTES)
	suspend fun getRandomQuote(): Response<QuotesItem>
}