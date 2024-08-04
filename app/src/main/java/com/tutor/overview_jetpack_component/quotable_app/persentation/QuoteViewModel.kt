package com.tutor.overview_jetpack_component.quotable_app.persentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutor.overview_jetpack_component.quotable_app.data.quote.Quotes
import com.tutor.overview_jetpack_component.quotable_app.utils.QuoteModule
import kotlinx.coroutines.launch
import retrofit2.HttpException

class QuoteViewModel
constructor(
//	private val repository: QuoteRepository,
//	@ApplicationContext private val context: Context
) : ViewModel() {
	private val apiQuote = QuoteModule.quoteApi

	private val _responseQuotes = MutableLiveData<QuoteResource<Quotes>>()
	val responseQuotes: MutableLiveData<QuoteResource<Quotes>> = _responseQuotes

	init {
		fetchQuoteLists()
	}

	fun fetchQuoteLists() {
		viewModelScope.launch {
			val response = try {
				apiQuote.getQuoteList()
			} catch (e: Exception) {
				e.printStackTrace()
				_responseQuotes.value = QuoteResource.Error("Something went wrong")
				return@launch
			} catch (e: HttpException) {
				e.printStackTrace()
				_responseQuotes.value = QuoteResource.Error(e.message.toString())
				return@launch
			}
			if (response.isSuccessful && response.body() != null) {
				response.body()
					?.let {
						_responseQuotes.value = QuoteResource.Success(it)
					}
			}

		}
	}
}
//
//class QuoteViewModelFactory(
//	private val apiQuote: QuotableApi,
//	private val repository: QuoteRepository,
//	private val context: Context
//) :
//	ViewModelProvider.Factory {
//	override fun <T : ViewModel> create(modelClass: Class<T>): T {
//		if (modelClass.isAssignableFrom(QuoteViewModel::class.java)) {
//			@Suppress("UNCHECKED_CAST")
//			return QuoteViewModel(
//				apiQuote,
//
////				repository,
////				context
//			) as T
//		}
//		throw IllegalArgumentException("Unknown ViewModel class")
//	}
//}
