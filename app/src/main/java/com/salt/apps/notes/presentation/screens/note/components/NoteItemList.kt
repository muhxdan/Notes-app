package com.salt.apps.notes.presentation.screens.note.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.salt.apps.notes.data.local.entity.NoteEntity
import com.salt.apps.notes.presentation.navigation.screen.AppScreen
import com.salt.apps.notes.presentation.screens.main.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItemList(
    note: NoteEntity,
    appNavController: NavHostController,
    mainViewModel: MainViewModel
) {
    val multipleEventsCutter = remember { MultipleEventsCutter.get() }
    Card(
        modifier = Modifier
            .bounceClick()
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .combinedClickable(
                onClick = {
                    handleNoteItemClick(note, appNavController, mainViewModel, multipleEventsCutter)
                },
                onLongClick = { mainViewModel.toggleSelectedNoteCard(note.id) },
            ),
        content = {
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier.padding(15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .fillMaxWidth()
                ) {
                    Text(
                        note.title.takeUnless { it.isNullOrBlank() } ?: "-",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = note.description.takeUnless { it.isNullOrBlank() } ?: "-",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = note.date,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Light)
                    )
                }
                if (mainViewModel.screenState.value.selectedNotes.isNotEmpty()) {
                    CircleCheckbox(
                        selected = mainViewModel.screenState.value.selectedNotes.contains(note.id),
                    )
                }
            }
        }
    )
}

private fun handleNoteItemClick(
    note: NoteEntity,
    appNavController: NavHostController,
    mainViewModel: MainViewModel,
    multipleEventsCutter: MultipleEventsCutter
) {

    if (mainViewModel.screenState.value.isAppBarOpened) {
        mainViewModel.toggleSelectedNoteCard(note.id)
    } else {
        multipleEventsCutter.processEvent {
            appNavController.navigate(
                AppScreen.Add.route.plus(
                    "?noteId=${note.id}&noteTitle=${note.title}&noteDescription=${note.description}&noteDate=${note.date}"
                )
            )
        }
    }
}