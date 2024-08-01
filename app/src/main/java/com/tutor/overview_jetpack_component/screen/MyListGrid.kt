package com.tutor.overview_jetpack_component.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutor.overview_jetpack_component.data.Movie
import com.tutor.overview_jetpack_component.data.movieLists
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme
import kotlinx.coroutines.launch

@Composable
fun MyListGrid(modifier: Modifier = Modifier) {
	Surface(
		modifier = modifier.fillMaxSize(),
	) {
//		MyListColumn()
//		MyLazyColumn()
//		MyLazyVerticalGrid()
		MyLazyBVerticalStraggledGrid()
	}
}

@Composable
fun MyListColumn(modifier: Modifier = Modifier) {
	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(6.dp)
			.verticalScroll(rememberScrollState()),
		verticalArrangement = Arrangement.spacedBy(6.dp)
	) {
		movieLists.forEach { MovieCard(movie = it) }
	}
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyLazyColumn(modifier: Modifier = Modifier) {
	val state = rememberLazyListState()
	val scope = rememberCoroutineScope()
	LazyColumn(
		modifier = modifier.fillMaxSize(),
		state = state,
		contentPadding = PaddingValues(6.dp),
		verticalArrangement = Arrangement.spacedBy(6.dp)
	) {
		stickyHeader {
			Text(
				modifier = modifier.fillMaxWidth(),
				text = "Total Items ${movieLists.size}",
				fontFamily = FontFamily.Monospace,
				fontWeight = FontWeight.Bold,
				textAlign = TextAlign.End
			)
		}
		items(movieLists, key = { it.id }) { MovieCard(movie = it) }
		item {
			TextButton(onClick = {
				scope.launch { state.animateScrollToItem(0) }
			}) {
				Text(
					text = "To Top",
					fontWeight = FontWeight.Bold,
					fontFamily = FontFamily.Monospace
				)
			}
		}
	}

}

@Composable
fun MyLazyVerticalGrid(modifier: Modifier = Modifier) {
	LazyVerticalGrid(
		modifier = modifier.fillMaxSize(),
		columns = GridCells.Fixed(2),
		contentPadding = PaddingValues(6.dp),
		horizontalArrangement = Arrangement.spacedBy(6.dp),
		verticalArrangement = Arrangement.spacedBy(6.dp),
	) {
		items(movieLists, key = { it.id }) { MovieCard(movie = it) }
	}
}

@Composable
fun MyLazyBVerticalStraggledGrid(modifier: Modifier = Modifier) {
	LazyVerticalStaggeredGrid(
		modifier = modifier.fillMaxSize(),
		columns = StaggeredGridCells.Adaptive(160.dp),
		contentPadding = PaddingValues(6.dp),
		horizontalArrangement = Arrangement.spacedBy(6.dp),
		verticalItemSpacing = 6.dp
	) {
		items(movieLists, key = { it.id }) { MovieCard(movie = it, maxLine = 12) }
	}

}

@Composable
fun MovieCard(
	movie: Movie,
	maxLine: Int = 4,
	modifier: Modifier = Modifier
) {
	Card(modifier = modifier.fillMaxWidth()) {
		Column(
			modifier = modifier
				.fillMaxWidth()
				.padding(horizontal = 12.dp, vertical = 8.dp)
		) {
			Text(
				text = "${movie.title} (${movie.year})",
				fontFamily = FontFamily.Serif,
				fontWeight = FontWeight.SemiBold,
				fontSize = 20.sp,
				maxLines = 1,
				overflow = TextOverflow.Ellipsis
			)
			Spacer(modifier = modifier.padding(4.dp))
			Text(
				text = movie.plot,
				fontSize = 18.sp,
				maxLines = maxLine,
				overflow = TextOverflow.Ellipsis
			)
		}
	}
}

@Preview
@Composable
private fun MovieCardPrev() {
	MovieCard(
		movie = movieLists[0]
	)

}

@Preview(showBackground = true)
@Composable
private fun MyListGridPrev() {
	OverviewJetpackComponentTheme { MyListGrid() }
}

@Preview(showBackground = true)
@Composable
private fun MyListColumnPrev() {
	OverviewJetpackComponentTheme { MyListColumn() }
}

@Preview(showBackground = true)
@Composable
private fun MyLazyColumnPrev() {
	OverviewJetpackComponentTheme { MyLazyColumn() }
}

@Preview(showBackground = true)
@Composable
private fun MyLazyVerticalGridPrev() {
	OverviewJetpackComponentTheme { MyLazyVerticalGrid() }
}
