package com.salt.apps.notes.presentation.screens.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.salt.apps.notes.presentation.components.Loading
import com.salt.apps.notes.presentation.screens.main.MainViewModel
import com.salt.apps.notes.presentation.screens.note.components.NoteList
import com.salt.apps.notes.util.State

@Composable
fun NoteScreen(
    appNavController: NavHostController,
    mainViewModel: MainViewModel,
    noteViewModel: NoteViewModel = hiltViewModel(),
) {
    val notesList by noteViewModel.notesList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (notesList is State.Loading) {
            Loading()
        } else {
            (notesList as State.Success).data?.let {
                mainViewModel.getAllSelectedNotes(it)
                NoteList(
                    notes = it,
                    appNavController = appNavController,
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}