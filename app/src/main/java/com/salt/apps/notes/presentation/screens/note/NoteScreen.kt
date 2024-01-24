package com.salt.apps.notes.presentation.screens.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.salt.apps.notes.presentation.components.Loading
import com.salt.apps.notes.presentation.components.NoteList
import com.salt.apps.notes.util.State

@Composable
fun NoteScreen(
    noteViewModel: NoteViewModel = hiltViewModel()
) {
    val notesList by noteViewModel.notesList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        if (notesList is State.Loading) {
            Loading()
        } else {
            (notesList as State.Success).data?.let { NoteList(notes = it) }
        }
    }
}