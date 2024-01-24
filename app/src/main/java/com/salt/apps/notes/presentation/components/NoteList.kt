package com.salt.apps.notes.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.salt.apps.notes.data.local.entity.NoteEntity

@Composable
fun NoteList(notes: List<NoteEntity>) {
    LazyColumn(
        modifier = Modifier.clip(MaterialTheme.shapes.medium),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(notes) { note ->
            NoteItemList(note = note)
        }
    }
}