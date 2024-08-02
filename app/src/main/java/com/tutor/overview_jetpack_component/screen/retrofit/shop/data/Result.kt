package com.tutor.overview_jetpack_component.screen.retrofit.shop.data

sealed class Result<T>(
	val data: T? = null,
	val message: String? = null
) {
	class Success<T>(data: T?) : Result<T>(data)
	class Error<T>(message: String, data: T? = null) : Result<T>(data, message)
//	class Loading<T> : Result<T>()
//	class Empty<T> : Result<T>()
//	class Idle<T> : Result<T>()
//	class None<T> : Result<T>()
//	class Cancelled<T> : Result<T>()
//
}
