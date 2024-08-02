package com.tutor.overview_jetpack_component.screen.retrofit.shop.data.model

data class Meta(
	val barcode: String,
	val createdAt: String,
	val qrCode: String,
	val updatedAt: String
)

val meta = Meta(
	barcode = "123456789012",
	qrCode = "QR_CODE_DATA",
	createdAt = "2023-08-01",
	updatedAt = "2023-08-01"
)
