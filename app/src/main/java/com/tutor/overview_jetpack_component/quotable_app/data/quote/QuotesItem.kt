package com.tutor.overview_jetpack_component.quotable_app.data.quote

data class QuotesItem(
	val _id: String,
	val author: String,
	val authorSlug: String,
	val content: String,
	val dateAdded: String,
	val dateModified: String,
	val length: Int,
	val tags: List<String>
)

