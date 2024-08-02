package com.tutor.overview_jetpack_component.screen.retrofit.shop.screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.tutor.overview_jetpack_component.screen.retrofit.shop.persentation.ProductRetroViewModel
import com.tutor.overview_jetpack_component.screen.retrofit.shop.persentation.ProductRetroViewModelFactory
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductNavigation(
//	viewModels: ProductRetroViewModel
) {
	val navController = rememberNavController()

	val viewModel = viewModel<ProductRetroViewModel>(
		factory = ProductRetroViewModelFactory(
//			ProductRepositoryImpl(ProductRetrofitApi.productApi)
		)
	)

//	val message = viewModel.messageRes.collectAsState(initial = "").value
	val products = viewModel.products.collectAsState().value

	val context = LocalContext.current
	val statusError = viewModel.showError
	LaunchedEffect(key1 = statusError) {
		statusError.collectLatest { show ->
			if (show) {
				Toast.makeText(context, "Error", Toast.LENGTH_LONG)
					.show()
			} else {
				Toast.makeText(context, "Success", Toast.LENGTH_LONG)
					.show()
			}
		}
	}
//	Text("$products")
	ProductRetroScreen(products, navController)
}

//@Preview
//@Composable
//private fun ProductNavigationPrev() {
////	ProductNavigation()
//}