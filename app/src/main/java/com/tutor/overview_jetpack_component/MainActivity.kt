package com.tutor.overview_jetpack_component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme
import dagger.hilt.android.AndroidEntryPoint

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		enableEdgeToEdge()
		setContent {
			OverviewJetpackComponentTheme {
				val navController = rememberNavController()

//				val userViewModel = viewModel<UserViewModel>(factory = UserViewModelFactory(userDatabase.dao))
//				MyNotification(applicationContext)
//				MyPreferenceDataBaseScreen(mainViewModel)
//				val state by viewModel.state.collectAsState()
//				MyContactScreen(state = state, onEvent = contactViewModel::onEvent)
//				TodoListPage(viewModel = todoViewModel)
//				NoteScreen(noteViewModel)
//				MemoScreen(
//					navController = navController,
//					viewModel = memoViewModel,
//				)
//				ShoppingNavigation(navController = navController)
//				ProductNav(
//					navController = navController,
//					viewModel = ProductViewModel
//				)
//				UserNavigationScreen(
//					navController = navController,
//					viewModel = userViewModel
//				)
//				ProductNavigation(
////					productRetrofitApi
//				)

			}
		}
	}

	//	private val productRetrofitApi by viewModels<ProductRetroViewModel>(factoryProducer = {
//		object : ViewModelProvider.Factory {
//			override fun <T : ViewModel> create(modelClass: Class<T>): T {
//				return ProductRetroViewModel(ProductRepositoryImpl(ProductRetrofitApi.productApi)) as T
//			}
//		}
//	})

}

