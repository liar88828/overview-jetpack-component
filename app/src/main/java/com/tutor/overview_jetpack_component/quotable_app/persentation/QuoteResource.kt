package com.tutor.overview_jetpack_component.quotable_app.persentation

sealed class QuoteResource<out T> {
	data class Success<out T>(val data: T?) : QuoteResource<T>()
	data class Error(val message: String) : QuoteResource<Nothing>()
	data object Loading : QuoteResource<Nothing>()
}