package com.tutor.overview_jetpack_component.movieApp.persentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutor.overview_jetpack_component.movieApp.domain.repository.MovieListRepository
import com.tutor.overview_jetpack_component.movieApp.utils.Category
import com.tutor.overview_jetpack_component.movieApp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel
@Inject constructor(
	private val repository: MovieListRepository
) : ViewModel() {
	private var _movieListState = MutableStateFlow(MovieListState())
	val movieListState = _movieListState.asStateFlow()

	init {
		getPopularMovieList(false)
		getUpcomingMovieList(false)
	}

	fun onEvent(event: MovieListEvent) {
		when (event) {
			MovieListEvent.Navigate -> {
				_movieListState.update {
					it.copy(
						isCurrentPopularScreen = !movieListState.value.isCurrentPopularScreen
					)
				}
			}

			is MovieListEvent.Paginate -> {
				if (event.category == Category.POPULAR) {
					getPopularMovieList(false)
				} else if (event.category == Category.UPCOMING) {
					getUpcomingMovieList(false)
				}

			}
		}

	}

	private fun getUpcomingMovieList(fetchForce: Boolean) {
		viewModelScope.launch {
			_movieListState.update {
				it.copy(isLoading = true)
			}
			repository.getMovieList(
				forceFetchFromRemote = fetchForce,
				category = Category.UPCOMING,
				page = movieListState.value.upComingMovieListPage
			)
				.collectLatest { result ->
					when (result) {
						is Resource.Error -> TODO()
						is Resource.Loading -> TODO()
						is Resource.Success -> TODO()
					}
				}
		}
	}

	private fun getPopularMovieList(fetchForce: Boolean) {

	}
}