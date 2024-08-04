package com.tutor.overview_jetpack_component.quotable_app.screen

sealed class QuoteRoute(val route: String) {
	data object Home : QuoteRoute("quote-home")
	data object Detail : QuoteRoute("quote-detail")
	data object Random : QuoteRoute("quote-random")
	data object Favorite : QuoteRoute("quote-favorite")
	data object Search : QuoteRoute("quote-search")
	data object Profile : QuoteRoute("quote-profile")
	data object About : QuoteRoute("quote-profile")
	data object Setting : QuoteRoute("quote-profile")

	fun withArgs(vararg args: String): String {
		return buildString {
			append(route)
			args.forEach { arg ->
				append("/$arg")
			}
		}
	}

	fun withArgsFormat(vararg args: String): String {
		return buildString {
			append(route)
			args.forEach { arg ->
				append("/{$arg}")

			}
		}
	}

}