package com.salt.apps.notes.presentation.screens.note.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.salt.apps.notes.data.local.entity.NoteEntity
import com.salt.apps.notes.presentation.screens.main.MainViewModel

@Composable
fun NoteList(
    notes: List<NoteEntity>,
    appNavController: NavHostController,
    mainViewModel: MainViewModel
) {
    Log.d("NoteList", "NoteList: ${notes.map { it.id }.toSet()}")
    LazyColumn(
        modifier = Modifier.clip(MaterialTheme.shapes.medium),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(notes) { note ->
            NoteItemList(
                note = note,
                appNavController = appNavController,
                mainViewModel = mainViewModel
            )
        }
    }
}