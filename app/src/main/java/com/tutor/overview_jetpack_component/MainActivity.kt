package com.tutor.overview_jetpack_component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.tutor.overview_jetpack_component.screen.preferences_datastore.MyMainModel
import com.tutor.overview_jetpack_component.screen.room_database.contact.ContactDatabase
import com.tutor.overview_jetpack_component.screen.room_database.contact.ContactViewModel
import com.tutor.overview_jetpack_component.screen.room_database.todo.TodoListPage
import com.tutor.overview_jetpack_component.screen.room_database.todo.TodoViewModel
import com.tutor.overview_jetpack_component.ui.theme.OverviewJetpackComponentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	private val mainViewModel by viewModels<MyMainModel>()
	private val db by lazy {
		Room.databaseBuilder(
			applicationContext,
			ContactDatabase::class.java,
			"contacts.db"
		).build()
	}
	private val viewModel by viewModels<ContactViewModel>(
		factoryProducer = {
			object : ViewModelProvider.Factory {
				override fun <T : ViewModel> create(modelClass: Class<T>): T {
					return ContactViewModel(db.dao) as T
				}
			}
		}
	)
	private lateinit var todoViewModel: TodoViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
		enableEdgeToEdge()
		setContent {
			OverviewJetpackComponentTheme {
//				MyNotification(applicationContext)
//				MyPreferenceDataBaseScreen(mainViewModel)
//				val state by viewModel.state.collectAsState()
//				MyContactScreen(state = state, onEvent = viewModel::onEvent)
				TodoListPage(viewModel = todoViewModel)
			}
		}
	}
}

