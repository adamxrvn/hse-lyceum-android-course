package com.adammz.hse_lyceum_room_notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.adammz.hse_lyceum_room_notes.model.Note
import com.adammz.hse_lyceum_room_notes.room.NoteDatabase
import com.adammz.hse_lyceum_room_notes.ui.theme.HSELyceumRoomNotesTheme
import com.adammz.hse_lyceum_room_notes.viewmodel.NoteViewModel
import com.adammz.hse_lyceum_room_notes.viewmodel.Repository

class MainActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "notes.db"
        ).build()
    }

    private val viewModel by viewModels<NoteViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NoteViewModel(Repository(database)) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HSELyceumRoomNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                    ) {

                    var title by rememberSaveable {
                        mutableStateOf("")
                    }

                    var text by rememberSaveable {
                        mutableStateOf("")
                    }

                    val note = Note(title, text)

                    var noteList by remember {
                        mutableStateOf(listOf<Note>())
                    }

                    viewModel.getNotes().observe(this) {
                        noteList = it
                    }

                    Scaffold(topBar = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Notes", style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.weight(1f),
                                fontSize = 17.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )

                            IconButton(onClick = { viewModel.sortNotes() }) {
                                Icon(
                                    imageVector = Icons.Default.List,
                                    contentDescription = "Sort",
                                    modifier = Modifier.size(36.dp),
                                    tint = Color.White

                                )
                            }
                        }

                    }

                    ) { paddingValues ->


                        Column(modifier = Modifier.padding(paddingValues)) {
                            Spacer(modifier = Modifier.height(16.dp))
                            TextField(value = title,
                                onValueChange = { title = it },
                                label = { Text("Title") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                placeholder = { Text("Title") }

                            )
                            TextField(value = text,
                                onValueChange = { text = it },
                                label = { Text("Text") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),

                                placeholder = { Text("Text") })

                            Button(modifier = Modifier
                                .fillMaxWidth()
                                .height(72.dp)
                                .padding(8.dp),
                                shape = RoundedCornerShape(8.dp),
                                onClick = { viewModel.upsert(note) }) {
                                Text("Add")

                            }

                            Spacer(modifier = Modifier.height(16.dp))



                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                items(noteList) { note ->
                                    NoteItem(
                                        note, viewModel
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun NoteItem(
    note: Note,
    viewModel: NoteViewModel
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()

            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = note.title, style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = note.text, style = MaterialTheme.typography.bodySmall)
        }

        IconButton(onClick = { viewModel.delete(note) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier.size(36.dp),

                )

        }
    }

}