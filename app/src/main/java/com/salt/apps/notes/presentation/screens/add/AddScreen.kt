package com.salt.apps.notes.presentation.screens.add

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.salt.apps.notes.data.local.entity.NoteEntity
import com.salt.apps.notes.presentation.screens.add.components.TextField

@Composable
fun AddScreen(
    note: NoteEntity,
    appNavController: NavHostController,
    addViewModel: AddViewModel = hiltViewModel(),
) {
    var title by remember { mutableStateOf(note.title.takeUnless { it.isNullOrBlank() } ?: "") }
    var description by remember {
        mutableStateOf(note.description.takeUnless { it.isNullOrBlank() } ?: "")
    }
    val currentTime by addViewModel.currentTime.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(
            navigateUp = {
                appNavController.navigateUp()
            },
            isActionsActive = title.trim().isNotEmpty() || description.trim().isNotEmpty(),
            isActionsClick = {
                addViewModel.insertNote(
                    id = 0,
                    title = title,
                    description = description
                )
                appNavController.navigateUp()
            }
        )
        TextField(title = title, placeHolder = "Title", onValueChange = { title = it })
        Text(text = note.date.takeUnless { it.isBlank() } ?: currentTime,
            style = MaterialTheme.typography.bodySmall.copy(
                color = if (isSystemInDarkTheme()) Color(0xff686868) else Color(0xFF8F8F8F)
            ),
            modifier = Modifier.padding(horizontal = 20.dp))

        Spacer(modifier = Modifier.height(15.dp))
        TextField(title = description,
            placeHolder = "Description",
            onValueChange = { description = it })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    isActionsActive: Boolean = false,
    isActionsClick: () -> Unit,
) {
    TopAppBar(
        title = {},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        actions = {
            IconButton(
                enabled = isActionsActive, onClick = isActionsClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Done, contentDescription = null
                )
            }
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = "Back"
                )
            }
        },
    )
}