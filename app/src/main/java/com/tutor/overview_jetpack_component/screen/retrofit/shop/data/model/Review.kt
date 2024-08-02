package com.tutor.overview_jetpack_component.screen.retrofit.shop.data.model

data class Review(
	val comment: String,
	val date: String,
	val rating: Int,
	val reviewerEmail: String,
	val reviewerName: String
)

val review = Review(
	comment = "This product is amazing!",
	rating = 5,
	reviewerEmail = "user@example.com",
	reviewerName = "John Doe",
	date = "2023-08-01"
)