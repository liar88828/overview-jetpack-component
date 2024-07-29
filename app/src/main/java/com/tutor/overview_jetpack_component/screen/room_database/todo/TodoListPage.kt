package com.tutor.overview_jetpack_component.screen.room_database.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale

@Composable
fun TodoListPage(
//	viewModel: () -> Unit,
	viewModel: TodoViewModel,
	modifier: Modifier = Modifier
) {
	val todoList by viewModel.todoList.observeAsState()
	var inputText by remember {
		mutableStateOf("")
	}

	fun handleSave() {
		viewModel.addTodo(inputText)
		inputText = ""
	}


	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(8.dp),
	) {
		Row(
			modifier = modifier
				.fillMaxWidth()
				.padding(8.dp),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			OutlinedTextField(
				modifier = modifier,
				value = inputText,
				onValueChange = { inputText = it })

			FloatingActionButton(
				modifier = modifier,
				onClick = { handleSave() },
			) {
				Icon(imageVector = Icons.Default.Add, contentDescription = "Add icons")
			}
		}

		LazyColumn() {
			item {
				TodoItem(
					item = Todo(
						title = "Todo 1",
						createAt = Date.from(Instant.now())
					),
					onDelete = { }
				)
			}
		}
		todoList?.let {
			LazyColumn(
				content = {
					itemsIndexed(it) { _, item ->
						TodoItem(
							item = item,
							onDelete = { viewModel.deleteTodo(item.id) }
						)
					}
				}
			)
		}
			?: Text(
				text = "No Items yet",
				modifier = modifier.fillMaxWidth(),
				textAlign = TextAlign.Center,
				fontSize = 16.sp
			)
	}
}

@Composable
fun TodoItem(
	item: Todo, onDelete: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.padding(8.dp)
			.clip(RoundedCornerShape(8.dp))
			.background(MaterialTheme.colorScheme.primary),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		Column(
			modifier = modifier
				.padding(10.dp),
			verticalArrangement = Arrangement.spacedBy(5.dp)
		) {
			Text(
				text = item.title,
				fontSize = 20.sp,
				color = Color.White
			)
			Text(
				text = SimpleDateFormat(
					"dd/MM/yyyy HH:mm:ss", Locale.TAIWAN
				).format(item.createAt),
				fontSize = 12.sp,
				color = MaterialTheme.colorScheme.onPrimary
			)
		}
		IconButton(onClick = onDelete) {
			Icon(
				imageVector = Icons.Default.Delete,
				contentDescription = "Delete",
				tint = Color.White
			)
		}
	}

}

@Preview
@Composable
private fun TodoItemPrev() {
	TodoItem(
		item = Todo(
			title = "Todo 1",
			createAt = Date.from(Instant.now())
		),
		onDelete = {}
	)

}
//@Preview
//@Composable
//private fun TodoListPagePrev() {
////	val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
//	TodoListPage(
//		viewModel = {}
//	)
//}
