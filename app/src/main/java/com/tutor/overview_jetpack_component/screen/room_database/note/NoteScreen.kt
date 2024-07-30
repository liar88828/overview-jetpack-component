package com.tutor.overview_jetpack_component.screen.room_database.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteScreen(noteViewModel: NoteViewModel) {
	Scaffold { paddingValue ->
		Surface(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValue),
			color = MaterialTheme.colorScheme.background
		) {
			var name by remember {
				mutableStateOf("")
			}
			var body by remember {
				mutableStateOf("")
			}
			val note = Note(name, body)
			val noteList by noteViewModel.getNotes().observeAsState()
			val notes = noteList ?: emptyList()

			fun onDelete(note: Note) {
				noteViewModel.deleteNote(note)
			}

			fun onUpsert(note: Note) {
				noteViewModel.upsertNote(note)
			}

			Column(
				modifier = Modifier
					.fillMaxWidth()
			) {
				Column(
					modifier = Modifier
						.fillMaxWidth()
						.padding(8.dp),
					verticalArrangement = Arrangement.spacedBy(8.dp)
				) {
					TextField(
						modifier = Modifier.fillMaxWidth(),
						placeholder = { Text("Add Name ...") },
						label = { Text("Name") },
						value = name,
						onValueChange = { name = it },
					)
					TextField(
						modifier = Modifier.fillMaxWidth(),
						placeholder = { Text("Add Body ...") },
						label = { Text("Body") },
						value = body,
						onValueChange = { body = it },
					)
					Button(
						modifier = Modifier.fillMaxWidth(),
						onClick = { onUpsert(note) }
					) { Text("Add") }
				}


				LazyColumn(
					contentPadding = PaddingValues(8.dp),
					verticalArrangement = Arrangement.spacedBy(8.dp)
				) {
					if (notes.isEmpty()) {
						item {
							Text(
								text = "No Notes yet",
								modifier = Modifier.fillMaxWidth(),
								textAlign = TextAlign.Center
							)
						}
					} else {
						items(notes) { note ->
							NoteItemScreen(note) { onDelete(note) }
						}
					}
				}
			}
		}
	}
}

@Composable
private fun NoteItemScreen(
	note: Note,
	onDelete: () -> Unit,
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.clip(RoundedCornerShape(24.dp))
			.background(MaterialTheme.colorScheme.primary)
			.padding(8.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		Text(
			text = "${note.id} : ${note.name}",
			fontSize = 20.sp,
			color = Color.White
		)
		Spacer(modifier = Modifier.size(8.dp))
		Text(
			text = note.body,
			fontSize = 18.sp,
			color = Color.White
		)
		IconButton(onClick = { onDelete() }) {
			Icon(
				imageVector = Icons.Default.Delete,
				contentDescription = "Delete Icons",
				tint = Color.White
			)
		}
	}
}