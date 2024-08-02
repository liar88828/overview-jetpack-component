package com.tutor.overview_jetpack_component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.tutor.overview_jetpack_component.screen.preferences_datastore.MyMainModel
import com.tutor.overview_jetpack_component.screen.retrofit.shop.screen.ProductNavigation
import com.tutor.overview_jetpack_component.screen.room_database.contact.ContactDatabase
import com.tutor.overview_jetpack_component.screen.room_database.contact.ContactViewModel
import com.tutor.overview_jetpack_component.screen.room_database.market.persentation.UserViewModel
import com.tutor.overview_jetpack_component.screen.room_database.memo.data.MemoDatabase
import com.tutor.overview_jetpack_component.screen.room_database.memo.persentation.MemoViewModel
import com.tutor.overview_jetpack_component.screen.room_database.note.NoteDatabase
import com.tutor.overview_jetpack_component.screen.room_database.note.NoteRepo
import com.tutor.overview_jetpack_component.screen.room_database.note.NoteViewModel
import com.tutor.overview_jetpack_component.screen.room_database.product.persentation.ProductViewModel
import com.tutor.overview_jetpack_component.screen.room_database.todo.TodoViewModel
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

		enableEdgeToEdge()
		setContent {
			OverviewJetpackComponentTheme {
				val contactState by contactViewModel.state.collectAsState()
				val userViewModel = viewModel(modelClass = UserViewModel::class.java)
				val ProductViewModel = viewModel(modelClass = ProductViewModel::class.java)
//				val userViewModel = viewModel<UserViewModel>(factory = UserViewModelFactory(userDatabase.dao))
				val navController = rememberNavController()
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
				ProductNavigation(
//					productRetrofitApi
				)
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

	private val memoDatabase by lazy {
		Room.databaseBuilder(
			applicationContext,
			MemoDatabase::class.java,
			"memos.db"
		)
			.build()
	}
	private val memoViewModel by viewModels<MemoViewModel>(
		factoryProducer = {
			object : ViewModelProvider.Factory {
				override fun <T : ViewModel> create(modelClass: Class<T>): T {
					return MemoViewModel(memoDatabase.dao) as T
				}
			}
		}
	)
	private val mainViewModel by viewModels<MyMainModel>()
	private val db by lazy {
		Room.databaseBuilder(
			applicationContext,
			ContactDatabase::class.java,
			"contacts.db"
		)
			.build()
	}
	private val contactViewModel by viewModels<ContactViewModel>(
		factoryProducer = {
			object : ViewModelProvider.Factory {
				override fun <T : ViewModel> create(modelClass: Class<T>): T {
					return ContactViewModel(db.dao) as T
				}
			}
		}
	)
	private lateinit var todoViewModel: TodoViewModel
	private val noteDatabase by lazy {
		Room.databaseBuilder(
			applicationContext,
			NoteDatabase::class.java,
			"notes.db"
		)
			.build()
	}
	private val noteViewModel by viewModels<NoteViewModel>(
		factoryProducer = {
			object : ViewModelProvider.Factory {
				override fun <T : ViewModel> create(modelClass: Class<T>): T {
					return NoteViewModel(NoteRepo(noteDatabase)) as T
				}
			}
		})

}

