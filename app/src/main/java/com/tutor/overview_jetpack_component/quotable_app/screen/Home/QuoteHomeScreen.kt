package com.tutor.overview_jetpack_component.quotable_app.screen.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tutor.overview_jetpack_component.component.MyFloatingActionButton
import com.tutor.overview_jetpack_component.component.MyNavigationBar
import com.tutor.overview_jetpack_component.component.MyTopBar
import com.tutor.overview_jetpack_component.quotable_app.data.quote.Quotes
import com.tutor.overview_jetpack_component.quotable_app.data.quote.QuotesItem
import com.tutor.overview_jetpack_component.quotable_app.persentation.QuoteResource
import com.tutor.overview_jetpack_component.quotable_app.persentation.QuoteViewModel

@Composable
fun QuoteHomeScreen(
	viewModel: QuoteViewModel,
	navController: NavHostController,
	modifier: Modifier
) {
	val randomQuoteState = viewModel.responseQuotes.observeAsState()
	val randomQuote = randomQuoteState.value ?: QuoteResource.Loading

	Scaffold(
		modifier = modifier.fillMaxSize(),
		bottomBar = { MyNavigationBar(modifier) },
		topBar = { MyTopBar() },
		floatingActionButton = { MyFloatingActionButton { viewModel.fetchQuoteLists() } }) { paddingValues ->
		Column(
			modifier = modifier
				.padding(paddingValues)
				.fillMaxSize()
				.padding(20.dp)
		) {
			Text(
				text = "Quote of the day ",
				modifier = modifier.padding(20.dp),
				fontSize = 30.sp,
				fontStyle = FontStyle.Italic,
			)
			RandomQuote(item = randomQuote)
//			Text("${randomQuoteState.value}")

		}
	}
}

@Composable
private fun RandomQuote(
	item: QuoteResource<Quotes>,
	modifier: Modifier = Modifier
) {

	when (item) {
		is QuoteResource.Error -> {
			Card(
				modifier = modifier
					.fillMaxWidth()
					.padding(20.dp)
			) { Text(text = item.message) }
		}

		QuoteResource.Loading -> CircularProgressIndicator()

		is QuoteResource.Success -> item.data?.let { quotes ->
			LazyColumn(
				modifier = modifier.fillMaxWidth(),
				verticalArrangement = Arrangement.spacedBy(20.dp)
			) {
				items(quotes.results) { quote ->
					QuoteCard(modifier, quote)
				}
			}
		}
	}
}

@Composable
private fun QuoteCard(
	modifier: Modifier,
	item: QuotesItem
) {
	Card(
		modifier = modifier
			.fillMaxWidth()

	) {
		Box(
			modifier = modifier
				.fillMaxWidth()
				.height(250.dp)
				.background(
					brush = Brush.verticalGradient(
						colors = listOf(
							MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
							MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
						)
					)
				)
		) {
			Column(
				modifier = modifier
					.fillMaxWidth()
					.padding(20.dp)
			) {
				Text(
					text = item.content,
					fontSize = 28.sp,
					fontWeight = FontWeight.Bold,
					fontStyle = FontStyle.Italic
				)
				Spacer(modifier = modifier.height(10.dp))
				Text(
					text = item.author,
					fontSize = 20.sp,
					fontWeight = FontWeight.SemiBold,
					fontStyle = FontStyle.Italic
				)
				Spacer(modifier = modifier.height(10.dp))
				Row(
					modifier = modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.Center,
					verticalAlignment = Alignment.Bottom
				) {

					Icon(
						imageVector = Icons.Default.FormatQuote,
						contentDescription = "Quote format",
						tint = MaterialTheme.colorScheme.onPrimary,
						modifier = modifier.size(60.dp)
					)
				}
			}
		}

	}
}

@Preview(showBackground = true)
@Composable
private fun QuoteCardPrev() {
	QuoteCard(
		modifier = Modifier,
		item = QuotesItem(
			_id = "1234",
			tags = listOf("life", "love"),
			length = 23,
			content = "Ferox, gratis elevatuss interdum acquirere de fatalis, bi-color luna.",
			author = "Yuan yuan Gong",
			dateModified = "2023-05-22",
			dateAdded = "2023-05-22",
			authorSlug = "yuan-yuan-gong"

		)

	)
}