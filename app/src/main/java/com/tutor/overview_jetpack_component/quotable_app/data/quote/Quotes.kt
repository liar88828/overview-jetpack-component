package com.tutor.overview_jetpack_component.quotable_app.data.quote

data class Quotes(
	val count: Int,
	val lastItemIndex: Int,
	val page: Int,
	val results: List<QuotesItem>,
	val totalCount: Int,
	val totalPages: Int
)